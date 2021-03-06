package com.example.gk.testretrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 项目名称：TestRetrofit
 * 类描述：请求用户信息 返回的内容是字符串
 * 创建人：gk
 * 创建时间：2017/2/15 10:18
 * 修改人：gk
 * 修改时间：2017/2/15 10:18
 * 修改备注：
 */
public interface PhoneCityQueryService {
    @POST("mobile/get")
    Call<String> getPhoneCityString(@Query("phone") String phone, @Query("key") String key);
}
