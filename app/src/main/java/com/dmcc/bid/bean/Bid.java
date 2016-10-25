package com.dmcc.bid.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wushange on 2016/10/24.
 * <p>
 * 搜索关键字返回的大bean
 */

public class Bid implements Serializable {


    /**
     * bidCountCurrent : 100
     * bidCountSum : 10000
     * firstPartys : {"北京荣程创新科技发展股份有限公司8":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司9":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司6":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司7":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司4":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司5":["标书aa","标书bb","标书dd"],"北京医科大学附属第三医院1":["标书a","标书b"],"北京医科大学附属第三医院2":["标书a","标书b"],"北京医科大学附属第三医院":["标书a","标书b"],"北京医科大学附属第三医院3":["标书a","标书b"],"北京医科大学附属第三医院4":["标书a","标书b"],"北京医科大学附属第三医院5":["标书a","标书b"],"北京医科大学附属第三医院6":["标书a","标书b"],"北京医科大学附属第三医院7":["标书a","标书b"],"北京医科大学附属第三医院9":["标书a","标书b"],"北京荣程创新科技发展股份有限公司":["标书aa","标书bb","标书dd"],"北京医科大学附属第三医院8":["标书a","标书b"],"北京荣程创新科技发展股份有限公司1":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司3":["标书aa","标书bb","标书dd"],"北京荣程创新科技发展股份有限公司2":["标书aa","标书bb","标书dd"]}
     * keyword : 服务器
     * secondPartys : {}
     */

    private int bidCountCurrent;
    private int bidCountSum;
    private HashMap<String, List<String>> firstPartys;
    private String keyword;
    private HashMap<String, List<String>> secondPartys;


    public int getBidCountCurrent() {
        return bidCountCurrent;
    }

    public void setBidCountCurrent(int bidCountCurrent) {
        this.bidCountCurrent = bidCountCurrent;
    }

    public int getBidCountSum() {
        return bidCountSum;
    }

    public void setBidCountSum(int bidCountSum) {
        this.bidCountSum = bidCountSum;
    }

    public HashMap<String, List<String>> getFirstPartys() {
        return firstPartys;
    }

    public void setFirstPartys(HashMap<String, List<String>> firstPartys) {
        this.firstPartys = firstPartys;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public HashMap<String, List<String>> getSecondPartys() {
        return secondPartys;
    }

    public void setSecondPartys(HashMap<String, List<String>> secondPartys) {
        this.secondPartys = secondPartys;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bidCountCurrent=" + bidCountCurrent +
                ", bidCountSum=" + bidCountSum +
                ", firstPartys=" + firstPartys +
                ", keyword='" + keyword + '\'' +
                ", secondPartys=" + secondPartys +
                '}';
    }
}
