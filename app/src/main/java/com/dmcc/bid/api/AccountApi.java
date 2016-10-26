package com.dmcc.bid.api;


import android.content.Context;

import com.dmcc.bid.bean.Login;
import com.dmcc.bid.components.retrofit.FastjsonConverterFactory;
import com.dmcc.bid.components.retrofit.RequestHelper;

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
public class AccountApi {

    static final String BASE_URL = "http://139.129.129.164:7990/bidAPI/";
    private AccountService mAccountService;
    private RequestHelper mRequestHelper;
    private Context mContext;

    public AccountApi(OkHttpClient mOkHttpClient, RequestHelper mRequestHelper, Context context) {
        this.mContext = context;
        this.mRequestHelper = mRequestHelper;
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(FastjsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mAccountService = retrofit.create(AccountService.class);
    }

    public Observable<Login> login(String userName, String passWord) {
        return toSubscribe(mAccountService.login(userName, passWord) );

    }


    private <T> Observable toSubscribe(Observable<T> o) {
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
