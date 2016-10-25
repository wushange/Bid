package com.dmcc.bid.api;


import android.content.Context;

import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.bean.BidInfo;
import com.dmcc.bid.components.retrofit.FastjsonConverterFactory;
import com.dmcc.bid.components.retrofit.RequestHelper;
import com.orhanobut.logger.Logger;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 接口定义
 *
 * @author wushange
 *         created at 2016/06/30 14:02
 */
public class BidApi {

    static final String BASE_URL = "http://115.29.49.187:8280/bidAPI/";
    private BidService mBidService;
    private RequestHelper mRequestHelper;
    private Context mContext;

    public BidApi(OkHttpClient mOkHttpClient, RequestHelper mRequestHelper, Context context) {
        this.mContext = context;
        this.mRequestHelper = mRequestHelper;
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(FastjsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mBidService = retrofit.create(BidService.class);
    }

    public Observable<Bid> searchKeyWord(String keyword, int page) {
        Logger.e("页码" + page);
        return toSubscribe(mBidService.searchKeyword(keyword, page, 25));
    }

    public Observable<List<BidInfo>> getBids(String party, int page) {
        Logger.e("页码" + page);
        return toSubscribe(mBidService.searchKeyword(party, page, 25));
    }

    private <T> Observable toSubscribe(Observable<T> o) {
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
