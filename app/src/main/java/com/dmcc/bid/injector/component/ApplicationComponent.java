package com.dmcc.bid.injector.component;

import android.content.Context;

import com.dmcc.bid.MyApplication;
import com.dmcc.bid.api.AccountApi;
import com.dmcc.bid.api.BidApi;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.components.okhttp.OkHttpHelper;
import com.dmcc.bid.components.retrofit.RequestHelper;
import com.dmcc.bid.injector.module.ApiModule;
import com.dmcc.bid.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by sll on 2016/3/8.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    Context getContext();

    AccountApi getAccountApi();

    BidApi getBidApi();

    OkHttpHelper getOkHttpHelper();

    RequestHelper getRequestHelper();

    void inject(MyApplication mApplication);

    void inject(BaseActivity mBaseActivity);

}
