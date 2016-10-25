package com.dmcc.bid.components.retrofit;

/**
 * Created by aresa on 2016/5/30.
 */
public class JsonResponse<T>   {
    public String resultCode;
    public String message;
    public int totalCount;
    public T data;
}
