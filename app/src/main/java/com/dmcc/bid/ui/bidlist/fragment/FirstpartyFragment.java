package com.dmcc.bid.ui.bidlist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseFragmentV4;
import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.bean.BidItem;
import com.dmcc.bid.ui.bidlist.BidListComponent;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * Created by wushange on 2016/10/24.
 */

public class FirstpartyFragment extends BaseFragmentV4 implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Override
    public void initInjector() {
        getComponent(BidListComponent.class).inject(this);
    }

    Bid bid;
    @BindView(R.id.tv_list_size)
    TextView mNamesCount;
    @BindView(R.id.tv_list_desc)
    TextView mDesc;
    @BindView(R.id.bid_list)
    EasyRecyclerView mRecyclerView;
    ListAdapter adapter;
    List<BidItem> bidItems = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.fragment_firstparty_view;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {
        bid = (Bid) parms.getSerializable("bid");
        Set keys = bid.getFirstPartys().keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                List<String> value = bid.getFirstPartys().get(key);
                BidItem item = new BidItem();
                item.setBidName(key);
                item.setBidId(value);
                bidItems.add(item);
            }

            Logger.e("---" + bidItems.size());
        }
    }

    @Override
    public void initView(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapterWithProgress(adapter = new ListAdapter(getActivity()));
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter.addAll(bidItems);
            }
        }, 1000);

    }

    @Override
    public void onLoadMore() {

    }
}
