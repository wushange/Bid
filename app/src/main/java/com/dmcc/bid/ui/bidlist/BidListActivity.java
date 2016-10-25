package com.dmcc.bid.ui.bidlist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.bean.BidItem;
import com.dmcc.bid.ui.bidinfolist.BidInfoListActivity;
import com.dmcc.bid.ui.bidlist.fragment.ListAdapter;
import com.dmcc.bid.util.PixelUtil;
import com.dmcc.bid.util.StringUtil;
import com.dmcc.bid.widget.CustomTextView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class BidListActivity extends BaseActivity implements BidListContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    final int mISFirst = 0;
    final int mISSecond = 1;
    @BindView(R.id.number_progress_bar)
    NumberProgressBar mNumberProBar;
    @BindView(R.id.et_bdlist_input)
    EditText mEdittext;
    @BindView(R.id.back_btn)
    Button back;
    @BindView(R.id.iv_bdlist_search)
    ImageView mSearch;
    @BindView(R.id.tv_bdlist_count)
    CustomTextView mSumCount;
    @BindView(R.id.tv_first_count)
    CustomTextView mFirstCount;
    @BindView(R.id.tv_second_count)
    CustomTextView mSceondCount;
    @BindView(R.id.tv_list_size)
    CustomTextView mBidlistCount;
    @BindView(R.id.erl_bidlist_view)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.tv_list_desc)
    TextView mtvListDesc;

    @Inject
    BidListPresenter presenter;
    @Inject
    ListAdapter adapter;
    String tempKeyWord = "";
    int page = 1;
    int currtParty = mISFirst;

    List<BidItem> bidItemList = new ArrayList<>();

    @Override
    public void initInjector() {
        DaggerBidListComponent.builder().activityModule(getActivityModule()).applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bidlist_main;
    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        initView();
    }


    @Override
    public void doBusiness(Context mContext) {
        presenter.searchKeyWord();
    }

    @Override
    public String getKeyword() {
        return mEdittext.getText().toString();
    }

    @Override
    public int getPageNo() {
        return page;
    }

    @Override
    public void onRefresh() {
        page = 1;
        textViewReset();
        presenter.searchKeyWord();
    }


    @Override
    public void onLoadMore() {
        presenter.searchKeyWord();
    }

    @Override
    public void searchSuccess(Bid bid) {
        if (bid != null) {
            showList(bid);
            mBaseOperation.dissLoading();
        } else {
            mBaseOperation.showSweetWarning("信息", "信息为空请重新输入");
        }
    }

    @Override
    public void searchError(String errinfo) {
        textViewReset();
        mBaseOperation.dissLoading();
//        mBaseOperation.showSweetWarning("暂无数据", errinfo);
    }

    @Override
    public void startLoading() {
        mBaseOperation.showSweetLoading("正在搜索...");
    }

    @Override
    public void endLoading() {
    }

    @Override
    public void showErrorInfo(String error) {
        mBaseOperation.getmSweetAlertDialog(SweetAlertDialog.WARNING_TYPE).setTitleText("错误信息").setContentText(error).show();
    }


    /**
     * 初始化试图
     */
    private void initView() {
        tempKeyWord = getIntent().getStringExtra("keyWords");
        mEdittext.setText(tempKeyWord);
        mEdittext.setSelection(tempKeyWord.length());
        mEdittext.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchKeyWord();
            }
            return false;
        });
        back.setOnClickListener(v -> finish());
        mSearch.setOnClickListener(v -> presenter.searchKeyWord());
        mtvListDesc.setOnClickListener(v -> {
            Comparator comp = (o1, o2) -> {//排序
                BidItem p1 = (BidItem) o1;
                BidItem p2 = (BidItem) o2;
                if (p1.getBidId().size() < p2.getBidId().size())
                    return 1;
                else if (p1.getBidId().size() == p2.getBidId().size())
                    return 0;
                else if (p1.getBidId().size() > p2.getBidId().size())
                    return -1;
                return 0;
            };
            adapter.sort(comp);

        });
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#E5E5E5"), PixelUtil.dp2px(getContext(), 0.5f), PixelUtil.dp2px(getContext(), 0), 0);//color & height & paddingLeft & paddingRight
        itemDecoration.setDrawLastItem(true);//sometimes you don't want draw the divider for the last item,default is true.
        itemDecoration.setDrawHeaderFooter(false);//whether draw divider for header and footer,default is false.
        mRecyclerView.setItemAnimator(new ScaleInBottomAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setEmptyView(R.layout.view_empty);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setRefreshListener(this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setMore(R.layout.view_more, this);
        adapter.setError(R.layout.view_error);
        adapter.setOnItemClickListener(position -> {
            mBaseOperation.addParameter("party_text", adapter.getItem(position));
            mBaseOperation.forward(BidInfoListActivity.class);
        });
        mFirstCount.setOnClickListener(v -> {
            currtParty = mISFirst;
            mFirstCount.setTextColor(R.color.white);
            mSceondCount.setTextColor(R.color.textSubColoe);
            presenter.searchKeyWord();
        });
        mSceondCount.setOnClickListener(v -> {
            currtParty = mISSecond;
            mFirstCount.setTextColor(R.color.textSubColoe);
            mSceondCount.setTextColor(R.color.white);
            presenter.searchKeyWord();
        });
    }

    /**
     * 显示列表数据 根据返回Bid
     *
     * @param bid
     */
    private void showList(Bid bid) {
        int tempCount = bid.getBidCountSum();
        int tempCurrent = bid.getBidCountCurrent();
        mSumCount.setTextTitle("共搜索到标书量:").setTextConetxt("" + tempCount).setLeftText("").setRightText("条").setTextColor(R.color.white);
        mNumberProBar.setProgress(Integer.parseInt(StringUtil.getPercent(tempCurrent, tempCount)));
        bidItemList = getBidItems(bid, currtParty);//解析map集合 区分甲乙方数据
        int firstSize = bid.getFirstPartys().size();
        int secondSize = bid.getSecondPartys().size();
        int textContext = 0;//机构名称
        int textColra = R.color.white;
        int textColrb = R.color.textSubColoe;
        if (currtParty == mISFirst) {
            textContext = firstSize;
            textColra = R.color.white;
            textColrb = R.color.textSubColoe;
        } else {
            textContext = secondSize;
            textColra = R.color.textSubColoe;
            textColrb = R.color.white;
        }
        mBidlistCount.setTextTitle("机构名称").setTextConetxt("" + (Integer.parseInt(mBidlistCount.getTextContext()) + textContext)).setTextColor(R.color.textSubColoe);
        mFirstCount.setTextTitle("甲方").setTextConetxt("" + (Integer.parseInt(mFirstCount.getTextContext()) + firstSize)).setTextColor(textColra);
        mSceondCount.setTextTitle("乙方").setTextConetxt("" + (Integer.parseInt(mSceondCount.getTextContext()) + secondSize)).setTextColor(textColrb);
        page++;
        adapter.addAll(bidItemList);
    }

    /**
     * 解析甲乙方  bidlist数据
     *
     * @param bid
     * @param ISFirst 区分甲乙方
     * @return
     */
    @NonNull
    private List<BidItem> getBidItems(Bid bid, int ISFirst) {
        List<BidItem> itemList = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        if (ISFirst == mISFirst) {
            Logger.e("读取甲方数据");
            map = bid.getFirstPartys();
        } else {
            Logger.e("读取乙方数据");
            map = bid.getSecondPartys();
        }
        Set keys = map.keySet();
        Logger.e("这是keys" + keys.toString());
        if (keys != null && keys.size() > 0) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                List<String> value = bid.getFirstPartys().get(key);
                BidItem item = new BidItem();
                item.setBidName(key);
                item.setBidId(value);
                itemList.add(item);
            }

        }
        return itemList;
    }

    /**
     * 重置 界面显示数量，因为加载更多的时候显示会变成0  所以先变成0然后加上上次显示的数据  就是列表总数据大小
     */
    private void textViewReset() {
        adapter.clear();
        mSumCount.reset();
        mNumberProBar.setProgress(0);
        mBidlistCount.reset();
        mFirstCount.reset();
        mSceondCount.reset();
    }

}
