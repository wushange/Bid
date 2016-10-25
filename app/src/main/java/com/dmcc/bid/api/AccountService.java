package com.dmcc.bid.api;

import com.dmcc.bid.bean.Login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 接口帮助类
 *
 * @author wushange
 *         created at 2016/06/30 14:01
 */
public interface AccountService {


    @FormUrlEncoded
    @POST("login")
    Observable<Login> login(@Field("username") String phone, @Field("password") String password);
}
