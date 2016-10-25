package com.dmcc.bid.injector.module;

import android.content.Context;

import com.dmcc.bid.api.AccountApi;
import com.dmcc.bid.api.BidApi;
import com.dmcc.bid.components.retrofit.RequestHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by sll on 2015/3/7.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public AccountApi provideAccountApi(@Named("api") OkHttpClient okHttpClient, RequestHelper requestHelper, Context mContext) {
        return new AccountApi(okHttpClient, requestHelper, mContext);
    }

    @Provides
    @Singleton
    public BidApi provideBidApi(@Named("api") OkHttpClient okHttpClient,RequestHelper requestHelper,Context context){
        return new BidApi(okHttpClient, requestHelper, context);
    }

}
