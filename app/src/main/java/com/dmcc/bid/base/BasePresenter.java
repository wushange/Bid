package com.dmcc.bid.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建presenter的时候 继承这个传入泛型View 主要做绑定view 和 一些初始化操作
 * Created by wushange on  2016/07/27.
 */
public abstract class BasePresenter<T> {
    public T mView;//View
    protected Subscription mSubscription;
    protected CompositeSubscription mCompositeSubscription;//使用compositesubcription 管理Subcription

    public void attachView(T view) {
        this.mView = view;
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();
    }


    public void detachView() {
        mCompositeSubscription.unsubscribe();//取消订阅
        mView = null;
    }

}
