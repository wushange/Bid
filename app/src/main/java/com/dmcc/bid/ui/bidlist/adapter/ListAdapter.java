package com.dmcc.bid.ui.bidlist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.dmcc.bid.bean.BidItem;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class ListAdapter extends RecyclerArrayAdapter<BidItem> {


    @Inject
    public ListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(parent);
    }
}
