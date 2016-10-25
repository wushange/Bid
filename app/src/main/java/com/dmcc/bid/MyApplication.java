package com.dmcc.bid;

import android.app.Application;
import android.content.Context;

import com.dmcc.bid.injector.component.ApplicationComponent;
import com.dmcc.bid.injector.component.DaggerApplicationComponent;
import com.dmcc.bid.injector.module.ApplicationModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by wushange on 2016/06/01.
 */
public class MyApplication extends Application {
    private static MyApplication INSTANCE;
    private ApplicationComponent mApplicationComponent;
    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context context;
    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        Bugly.init(getApplicationContext(), "900057087", false);
        Logger.init("WUSHANGE").logLevel(LogLevel.FULL);
        context = this;
    }


    private void initComponent() {
        mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static MyApplication getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MyApplication();
        }
        return INSTANCE;
    }


    /**
     * 对外提供Application Context
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }


}
