package com.dmcc.bid.ui.bidlist.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.bean.BidItem;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class ListViewHolder extends BaseViewHolder<BidItem> {
    @BindView(R.id.tv_item_result_name)
    TextView mBidName;
    @BindView(R.id.tv_item_result_size)
    TextView mBidCount;


    public ListViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_bid_result_view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final BidItem bid) {
        mBidName.setText("" + bid.getBidName());
        mBidCount.setText("" + bid.getBidId().size());

    }
}
