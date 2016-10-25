package com.dmcc.bid.ui.bidinfolist;


import com.dmcc.bid.injector.PerActivity;
import com.dmcc.bid.injector.component.ApplicationComponent;
import com.dmcc.bid.injector.module.ActivityModule;

import dagger.Component;

/**
 * Created by sll on 2016/5/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface BidInfoListComponent {
    void inject(BidInfoListActivity activity);
}
