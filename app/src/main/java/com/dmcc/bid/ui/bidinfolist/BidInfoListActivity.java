package com.dmcc.bid.ui.bidinfolist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.bean.BidInfo;
import com.dmcc.bid.bean.BidItem;
import com.dmcc.bid.ui.bidinfolist.adapter.InfoAdapter;
import com.dmcc.bid.util.PixelUtil;
import com.dmcc.bid.widget.AppTitle;
import com.dmcc.bid.widget.x5webview.WebViewActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;
import me.grantland.widget.AutofitTextView;

public class BidInfoListActivity extends BaseActivity implements BidInfoListContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.apptitle)
    AppTitle appTitle;
    @BindView(R.id.bidinfo_list)
    EasyRecyclerView mRecyclerView;

    @BindView(R.id.tv_bdinfo_name)
    AutofitTextView mBidName;
    @BindView(R.id.tv_bdinfolist_history_size)
    TextView mHistorySize;
    @Inject
    BidInfoListPresenter presenter;
    @Inject
    InfoAdapter adapter;
    BidItem mParty;// 公司名称 上个界面列表的key
    int page = 1;

    @Override
    public void initInjector() {
        DaggerBidInfoListComponent.builder().activityModule(getActivityModule()).applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bidinfolist_main;
    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        mParty = (BidItem) getIntent().getSerializableExtra("party_text");
        Logger.e("列表界面传过来的公司名称为" + mParty.toString() + getKeyWord());
        appTitle.setCenterTitle("历史标讯").setLeftButtonClickListener(v -> finish()).setOnDoubleClickListener(view1 -> {
            if (mRecyclerView.getRecyclerView().getChildAt(0).getTop() == 0) {
                mRecyclerView.setRefreshing(true);
                onRefresh();
            }
            mRecyclerView.getRecyclerView().smoothScrollToPosition(0);
        });
        mBidName.setText(mParty.getBidName());
        mHistorySize.setText("(" + mParty.getBidId().size() + "条)");
        DividerDecoration itemDecoration = new DividerDecoration(
                Color.parseColor("#f4f4f4"),
                PixelUtil.dp2px(getContext(), 15),
                0,
                0);//color & height & paddingLeft & paddingRight
        itemDecoration.setDrawLastItem(true);//sometimes you don't want draw the divider for the last item,default is true.
        itemDecoration.setDrawHeaderFooter(true);//whether draw divider for header and footer,default is false.
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
            WebViewActivity.startActivity(this, "https://github.com/wushge11");
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        presenter.searchBidList();
    }


    @Override
    public void onRefresh() {
        page = 1;
        adapter.clear();
        presenter.searchBidList();
    }


    @Override
    public void onLoadMore() {
        page++;
        presenter.searchBidList();
    }

    @Override
    public int getPageNo() {
        return page;
    }

    @Override
    public String getBidName() {
        return mParty.getBidName();
    }

    @Override
    public String getKeyWord() {
        return getIntent().getStringExtra("keyword");
    }

    @Override
    public String getType() {
        return getIntent().getStringExtra("type");
    }

    @Override
    public void searchSuccess(List<BidInfo> infos) {
        adapter.addAll(infos);
    }

    @Override
    public void searchError(String errinfo) {

    }

    @Override
    public void startLoading() {
        mRecyclerView.showProgress();

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showErrorInfo(String error) {

    }
}
