package com.dmcc.bid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wushange on 2016/10/24.
 * <p>
 * <p>
 * 搜索关键字列表 item bean
 */

public class BidItem implements Serializable {

    String bidName;
    List<String> bidId;

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public List<String> getBidId() {
        return bidId;
    }

    public void setBidId(List<String> bidId) {
        this.bidId = bidId;
    }

    @Override
    public String toString() {
        return "BidItem{" +
                "bidName='" + bidName + '\'' +
                ", bidId=" + bidId +
                '}';
    }
}
