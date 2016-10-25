package com.dmcc.bid.ui.bidinfolist;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.bean.BidItem;
import com.dmcc.bid.widget.AppTitle;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
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
        Logger.e("列表界面传过来的公司名称为" + mParty.toString());
        appTitle.setCenterTitle("历史标讯").setLeftButtonClickListener(v -> finish());
        mBidName.setText(mParty.getBidName());
        mHistorySize.setText("(" + mParty.getBidId().size() + "条)");
    }


    @Override
    public void doBusiness(Context mContext) {
        presenter.searchBidList();
    }


    @Override
    public void onRefresh() {
    }


    @Override
    public void onLoadMore() {
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
    public void searchSuccess(Bid bid) {

    }

    @Override
    public void searchError(String errinfo) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showErrorInfo(String error) {

    }
}
