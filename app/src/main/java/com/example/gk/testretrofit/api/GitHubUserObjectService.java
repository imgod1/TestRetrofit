package com.example.gk.testretrofit.api;

import com.example.gk.testretrofit.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：TestRetrofit
 * 类描述：请求用户信息 返回的内容是Gson对象
 * 创建人：gk
 * 创建时间：2017/2/15 10:18
 * 修改人：gk
 * 修改时间：2017/2/15 10:18
 * 修改备注：
 */
public interface GitHubUserObjectService {
    @GET("users/{user}")
    Call<GithubUser> getUserObject(@Path("user") String user);
}
