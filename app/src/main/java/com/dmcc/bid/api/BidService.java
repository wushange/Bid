package com.dmcc.bid.api;

import com.dmcc.bid.bean.Bid;
import com.dmcc.bid.bean.BidInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口帮助类
 *
 * @author wushange
 *         created at 2016/06/30 14:01
 */
public interface BidService {


    @GET("getParty")
    Observable<Bid> searchKeyword(@Query("keyword") String keyword, @Query("page") int page, @Query("pages") int pages);

    @GET("getBids")
    Observable<List<BidInfo>> getBids(@Query("party") String party, @Query("page") int page, @Query("pages") int pages);
}
