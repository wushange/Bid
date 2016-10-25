package com.dmcc.bid.ui.bidlist;

import android.content.Context;

import com.dmcc.bid.api.BidApi;
import com.dmcc.bid.base.BasePresenter;
import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.injector.PerActivity;
import com.dmcc.bid.util.StringUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * p层
 */
@PerActivity
public class BidListPresenter extends BasePresenter<BidListContract.View> implements BidListContract.Prestener {

    private Context context;
    private BidApi bidApi;

    /**
     * 登陆界面构造器
     */
    @Inject
    public BidListPresenter(BidApi api, Context context) {
        this.bidApi = api;
        this.context = context;
    }


    @Override
    public void searchKeyWord() {

        if (StringUtil.isBlank(mView.getKeyword())) {

            mView.showErrorInfo("关键词不能为空！");
            return;
        }

        mView.startLoading();
        mCompositeSubscription.add(bidApi.searchKeyWord(mView.getKeyword(), mView.getPageNo()).subscribe(new Subscriber<Bid>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e("错误信息" + e.toString());
                mView.searchError("未知错误");
            }

            @Override
            public void onNext(Bid stringResponse) {
                Logger.e("搜索结果" + stringResponse.toString());
                mView.searchSuccess(stringResponse);

            }
        }));

    }

}
