package com.dmcc.bid.ui.bidlist;

import com.dmcc.bid.base.BaseView;
import com.dmcc.bid.bean.Bid;

/**
 * Created by wushange on 2016/10/21.
 */

public class BidListContract {

    public interface View extends BaseView {
        String getKeyword();
        int getPageNo();

        void searchSuccess(Bid bid);

        void searchError(String errinfo);
    }

    public interface Prestener {
        void searchKeyWord();
    }
}
