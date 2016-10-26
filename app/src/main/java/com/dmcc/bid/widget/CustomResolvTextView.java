package com.dmcc.bid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.orhanobut.logger.Logger;

/**
 * Created by wushange on 2016/10/26.
 */

public class CustomResolvTextView extends LinearLayout {
    ImageView ivCstImg;
    TextView tvCstTwo;
    TextView tvCstOne;
    private LayoutInflater mInflater;
    private View mView;

    public CustomResolvTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomResolvTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.custom_revolve_text, null);
        addView(mView);
        initViews(context);
    }

    /**
     * 初始化view
     */
    public void initViews(Context context) {
        ivCstImg = (ImageView) findViewByHeaderId(R.id.iv_cst_img);
        tvCstOne = (TextView) findViewByHeaderId(R.id.tv_cst_one);
        tvCstTwo = (TextView) findViewByHeaderId(R.id.tv_cst_two);
    }


    public View findViewByHeaderId(int id) {
        return mView.findViewById(id);
    }


    public CustomResolvTextView setText(int img, String str) {
        String temp1 = "";
        String temp2 = "";
        if (str.length() > 50) {
            Logger.e("文字大于50" + str.length());
            temp1 = str.substring(0, 24);
            temp2 = str.substring(24, str.length() - 1);
        } else {
            temp1 = str;
        }

        tvCstOne.setText(temp1);
        tvCstTwo.setText(temp2);

        return this;
    }

}
