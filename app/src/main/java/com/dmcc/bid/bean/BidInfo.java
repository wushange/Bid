package com.dmcc.bid.bean;

/**
 * Created by wushange on 2016/10/25.
 * <p>
 * 搜索公司下标书列表bean
 */

public class BidInfo {


    /**
     * province : 江苏省
     * time : 2016-10-09 18:36:25.0
     * title : [询价采购] 关于邗江区城建档案馆全封闭橱式档案密集架及二台70平米除湿机询价采购的公告
     * type : 招标
     * usid : 122193090
     */

    private String province;
    private String time;
    private String title;
    private String type;
    private String usid;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    @Override
    public String toString() {
        return "BidInfo{" +
                "province='" + province + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", usid='" + usid + '\'' +
                '}';
    }
}
