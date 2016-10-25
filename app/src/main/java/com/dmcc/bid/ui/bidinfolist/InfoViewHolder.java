package com.dmcc.bid.ui.bidinfolist;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.bean.BidInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;

/**
 * Created by wushange on 2016/10/25.
 */

public class InfoViewHolder extends BaseViewHolder<BidInfo> {
    @BindView(R.id.atv_bid_name)
    AutofitTextView atvBidName;
    @BindView(R.id.tv_bid_location)
    TextView tvBidLocation;
    @BindView(R.id.tv_bid_time)
    TextView tvBidTime;

    public InfoViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_bidinfo_list_view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(BidInfo data) {
        atvBidName.setText(data.getTitle());
        tvBidTime.setText("  "+data.getDate());
        tvBidLocation.setText("  "+data.getAddress());
    }
}
