package com.dmcc.bid.ui.bidinfolist;

import android.content.Context;
import android.view.ViewGroup;

import com.dmcc.bid.bean.BidInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

/**
 * Created by wushange on 2016/10/25.
 */

public class InfoAdapter extends RecyclerArrayAdapter<BidInfo> {
    @Inject
    public InfoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(parent);
    }
}
