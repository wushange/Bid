package com.dmcc.bid.ui.bidinfolist;

import com.dmcc.bid.base.BaseView;
import com.dmcc.bid.bean.BidInfo;

import java.util.List;

/**
 * Created by wushange on 2016/10/21.
 */

public class BidInfoListContract {

    public interface View extends BaseView {
        int getPageNo();

        String getBidName();

        String getKeyWord();

        String getType();

        void searchSuccess(List<BidInfo> infos);

        void searchError(String errinfo);
    }

    public interface Prestener {
        void searchBidList();
    }
}
