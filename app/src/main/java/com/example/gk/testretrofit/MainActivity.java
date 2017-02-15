package com.example.gk.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gk.testretrofit.api.Api;
import com.example.gk.testretrofit.api.GitHubReposListObjectService;
import com.example.gk.testretrofit.api.GitHubUserObjectService;
import com.example.gk.testretrofit.api.GitHubUserService;
import com.example.gk.testretrofit.api.PhoneCityQueryService;
import com.example.gk.testretrofit.model.GithubRepos;
import com.example.gk.testretrofit.model.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String GITHUB_USER_NAME = "imgod1";
    private TextView txt_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txt_content = (TextView) findViewById(R.id.txt_content);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_string:
                getUserString();
                break;
            case R.id.btn_get_object:
                getUserObject();
                break;
            case R.id.btn_get_repos_list_object:
                getReposListObject();
                break;
            case R.id.btn_post_phone_city_query:
                getPhoneCityString();
                break;
            default:
                break;
        }
    }

    /**
     * 返回结果为String
     */
    public void getUserString() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//必须加对字符串结果的支持,不然会崩
                .build();
        GitHubUserService gitHubUserService = retrofit.create(GitHubUserService.class);
        Call<String> userString = gitHubUserService.getUserString(GITHUB_USER_NAME);
        userString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("test", "onResponse:" + response.body());
                txt_content.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("test", "onFailure:" + t.getMessage());
                txt_content.setText(t.getMessage());
            }
        });
    }

    /**
     * 返回结果为Object
     */
    public void getUserObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//明确知道返回Gson类型的话.可以只添加下面的转换
                .addConverterFactory(GsonConverterFactory.create())//Gson转换
                .build();
        GitHubUserObjectService gitHubUserObjectService = retrofit.create(GitHubUserObjectService.class);
        Call<GithubUser> githubUserCall = gitHubUserObjectService.getUserObject(GITHUB_USER_NAME);
        githubUserCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser githubUser = response.body();
                Log.e("test", "onResponse:" + githubUser.getLogin() + "\t:\t" + githubUser.getPublic_repos());
                txt_content.setText(githubUser.toString());
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.e("test", "onFailure:" + t.getMessage());
                txt_content.setText(t.getMessage());
            }
        });
    }

    /**
     * 返回结果为仓库集合
     */
    public void getReposListObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//明确知道返回Gson类型的话.可以只添加下面的转换
                .addConverterFactory(GsonConverterFactory.create())//Gson转换
                .build();
        GitHubReposListObjectService gitHubReposListObjectService = retrofit.create(GitHubReposListObjectService.class);
        Call<List<GithubRepos>> githubUserCall = gitHubReposListObjectService.getReposListObject(GITHUB_USER_NAME);
        githubUserCall.enqueue(new Callback<List<GithubRepos>>() {
            @Override
            public void onResponse(Call<List<GithubRepos>> call, Response<List<GithubRepos>> response) {
                List<GithubRepos> reposList = response.body();
                Log.e("test", "onResponse:仓库数量:" + reposList.size());
                txt_content.setText("仓库数量:" + reposList.size());
                for (GithubRepos githubRepos : reposList) {
                    Log.e("test", "for:" + githubRepos.getFull_name() + "\t" + githubRepos.getDescription());
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepos>> call, Throwable t) {
                Log.e("test", "onFailure:" + t.getMessage());
                txt_content.setText(t.getMessage());
            }
        });
    }

    /**
     * 返回结果为手机归属地字符串
     */
    public void getPhoneCityString() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.JUHE_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//明确知道返回Gson类型的话.可以只添加下面的转换
                .addConverterFactory(GsonConverterFactory.create())//Gson转换
                .build();
        PhoneCityQueryService phoneCityQueryService = retrofit.create(PhoneCityQueryService.class);
        Call<String> phoneCityStringCall = phoneCityQueryService.getPhoneCityString("13866668888", "088f7b93f9a7d30e860259f33fdf07da");
        phoneCityStringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("test", "onResponse:" + response.body());
                txt_content.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("test", "onFailure:" + t.getMessage());
                txt_content.setText(t.getMessage());
            }
        });
    }
}
