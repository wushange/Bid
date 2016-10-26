package com.dmcc.bid.ui.bidinfolist.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.bean.BidInfo;
import com.dmcc.bid.widget.MixtureTextView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wushange on 2016/10/25.
 */

public class InfoViewHolder1 extends BaseViewHolder<BidInfo> {
    @BindView(R.id.tv_bid_location)
    TextView tvBidLocation;
    @BindView(R.id.tv_bid_time)
    TextView tvBidTime;
    @BindView(R.id.tv_test)
    MixtureTextView customResolvTextView;

    public InfoViewHolder1(ViewGroup parent) {
        super(parent, R.layout.item_bidinfo_list_view1);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(BidInfo data) {
        tvBidTime.setText("  " + data.getTime());
        tvBidLocation.setText("  " + data.getProvince());
        customResolvTextView.setText(  data.getTitle());
//        switch (data.getType()) {
//            case "招标":
//                ivBidType.setImageResource(R.mipmap.icon_bid_ing);
//                break;
//            case "中标":
//                ivBidType.setImageResource(R.mipmap.icon_bid_right);
//                break;
//            case "改标":
//                ivBidType.setImageResource(R.mipmap.icon_bid_alter);
//                break;
//
//        }
    }
}
