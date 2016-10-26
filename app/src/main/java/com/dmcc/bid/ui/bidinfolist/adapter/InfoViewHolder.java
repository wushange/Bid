package com.dmcc.bid.ui.bidinfolist.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.bean.BidInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wushange on 2016/10/25.
 */

public class InfoViewHolder extends BaseViewHolder<BidInfo> {
    @BindView(R.id.atv_bid_name)
    TextView atvBidName;
    @BindView(R.id.tv_bid_location)
    TextView tvBidLocation;
    @BindView(R.id.tv_bid_time)
    TextView tvBidTime;
    @BindView(R.id.iv_bid_type)
    ImageView ivBidType;

    public InfoViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_bidinfo_list_view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(BidInfo data) {
        atvBidName.setText(data.getTitle());
        tvBidTime.setText("  " + data.getTime());
        tvBidLocation.setText("  " + data.getProvince());
        switch (data.getType()) {
            case "招标":
                ivBidType.setImageResource(R.mipmap.icon_bid_ing);
                break;
            case "中标":
                ivBidType.setImageResource(R.mipmap.icon_bid_right);
                break;
            case "改标":
                ivBidType.setImageResource(R.mipmap.icon_bid_alter);
                break;

        }
    }
}
