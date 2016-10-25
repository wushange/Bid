package com.dmcc.bid.ui.bidlist;


import com.dmcc.bid.injector.PerActivity;
import com.dmcc.bid.injector.component.ApplicationComponent;
import com.dmcc.bid.injector.module.ActivityModule;
import com.dmcc.bid.ui.bidlist.fragment.FirstpartyFragment;
import com.dmcc.bid.ui.bidlist.fragment.SecondtpartyFragment;

import dagger.Component;

/**
 * Created by sll on 2016/5/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface BidListComponent {
    void inject(BidListActivity activity);

    void inject(FirstpartyFragment firstpartyFragment);

    void inject(SecondtpartyFragment secondtpartyFragment);
}
