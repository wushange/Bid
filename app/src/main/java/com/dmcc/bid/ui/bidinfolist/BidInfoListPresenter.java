package com.dmcc.bid.ui.bidinfolist;

import android.content.Context;

import com.dmcc.bid.api.BidApi;
import com.dmcc.bid.base.BasePresenter;
import com.dmcc.bid.bean.BidInfo;
import com.dmcc.bid.injector.PerActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * p层
 */
@PerActivity
public class BidInfoListPresenter extends BasePresenter<BidInfoListContract.View> implements BidInfoListContract.Prestener {

    private Context context;
    private BidApi bidApi;

    /**
     * 登陆界面构造器
     */
    @Inject
    public BidInfoListPresenter(BidApi api, Context context) {
        this.bidApi = api;
        this.context = context;
    }

    @Override
    public void searchBidList() {
        mView.startLoading();
        mCompositeSubscription.add(bidApi.getBids(mView.getBidName(), mView.getKeyWord(), mView.getType(), mView.getPageNo()).subscribe(new Subscriber<List<BidInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e("获取历史出错" + e.getMessage().toString());
                mView.searchError(e.getMessage().toString());
            }

            @Override
            public void onNext(List<BidInfo> bidInfos) {
                Logger.e("返回列表" + bidInfos.toString());
                mView.searchSuccess(bidInfos);
            }
        }));

    }
}
