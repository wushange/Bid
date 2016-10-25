package com.dmcc.bid.ui.bidlist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseFragmentV4;
import com.dmcc.bid.ui.bidlist.BidListComponent;
import com.orhanobut.logger.Logger;

/**
 * Created by wushange on 2016/10/24.
 */

public class SecondtpartyFragment extends BaseFragmentV4 {
    @Override
    public void initInjector() {
        getComponent(BidListComponent.class).inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_secondparty_view;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        Logger.e("lazyInitBusiness");
    }
}
