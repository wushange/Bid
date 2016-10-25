package com.dmcc.bid.ui.bidinfolist;

import com.dmcc.bid.base.BaseView;
import com.dmcc.bid.bean.Bid;

/**
 * Created by wushange on 2016/10/21.
 */

public class BidInfoListContract {

    public interface View extends BaseView {
        int getPageNo();

        String getBidName();

        void searchSuccess(Bid bid);

        void searchError(String errinfo);
    }

    public interface Prestener {
        void searchBidList();
    }
}
