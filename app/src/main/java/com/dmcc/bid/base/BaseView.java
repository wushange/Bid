package com.dmcc.bid.base;

/**
 *
 *Created by wushange on  2016/06/30.
 */
public interface BaseView {
    void startLoading();

    void endLoading();

    void showErrorInfo(String error);

}
