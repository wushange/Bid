package com.dmcc.bid.ui.login;


import com.dmcc.bid.injector.PerActivity;
import com.dmcc.bid.injector.component.ApplicationComponent;
import com.dmcc.bid.injector.module.ActivityModule;
import com.dmcc.bid.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by sll on 2016/5/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);

    void inject(SplashActivity activity);
}
