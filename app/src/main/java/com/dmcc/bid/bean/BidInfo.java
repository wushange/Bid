package com.dmcc.bid.bean;

/**
 * Created by wushange on 2016/10/25.
 * <p>
 * 搜索公司下标书列表bean
 */

public class BidInfo {


    /**
     * address : 北京
     * date : 2016-10-10
     * id : 22
     * title : 标22
     * type : 0
     */

    private String address;
    private String date;
    private String id;
    private String title;
    private String type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "BidInfo{" +
                "address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
