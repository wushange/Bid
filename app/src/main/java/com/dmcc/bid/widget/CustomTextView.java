package com.dmcc.bid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmcc.bid.R;

/**
 * Created by wushange on 2016/10/24.
 */

public class CustomTextView extends LinearLayout {

    private LayoutInflater mInflater;
    private View mView;
    private TextView mTitle;//标题
    private TextView mContext;//右侧文本

    private TextView mLeftC;
    private TextView mRightc;

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.custom_textview, null);
        addView(mView);
        initViews(context);
    }

    /**
     * 初始化view
     */
    public void initViews(Context context) {
        mTitle = (TextView) findViewByHeaderId(R.id.tv_title);
        mContext = (TextView) findViewByHeaderId(R.id.tv_context);
        mLeftC = (TextView) findViewByHeaderId(R.id.tv_left_c);
        mRightc = (TextView) findViewByHeaderId(R.id.tv_right_c);
    }


    public View findViewByHeaderId(int id) {
        return mView.findViewById(id);
    }

    public CustomTextView setTextTitle(String textTitle) {
        mTitle.setText(textTitle);
        return this;
    }

    public CustomTextView setTextConetxt(String conetxt) {
        mContext.setText(conetxt);
        return this;
    }

    public CustomTextView setLeftText(String conetxt) {
        mLeftC.setText(conetxt);
        return this;
    }

    public CustomTextView setRightText(String conetxt) {
        mRightc.setText(conetxt);
        return this;
    }

    public void setTextSize(int size) {
        mRightc.setTextSize(getResources().getDimension(size));
        mLeftC.setTextSize(getResources().getDimension(size));
        mTitle.setTextSize(getResources().getDimension(size));
        mContext.setTextSize(getResources().getDimension(size));

    }

    public void setTextColor(int color) {
        mRightc.setTextColor(getResources().getColor(color));
        mLeftC.setTextColor(getResources().getColor(color));
        mTitle.setTextColor(getResources().getColor(color));
        mContext.setTextColor(getResources().getColor(color));
    }

    public void reset() {
//        mTitle.setText("");
        mContext.setText("0");
    }

    public String getTextContext() {

        return mContext.getText().toString();
    }
}
