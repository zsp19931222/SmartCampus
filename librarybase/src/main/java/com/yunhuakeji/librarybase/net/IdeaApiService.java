package com.yunhuakeji.librarybase.net;


import com.yunhuakeji.librarybase.BuildConfig;
import com.yunhuakeji.librarybase.net.entity.LoginEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
* @Description:请求地址
* @Author:Andy
* @Date:2019/6/25 0025 14:57
*/
public interface IdeaApiService {
//1811004124  033927 冯越
//    2018010062 033520 李洲洲
//    2010010117 091016
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 8 * 1000;


    String PROJECT_NAME = BuildConfig.PROJECT_NAME;//项目名称
    String H5_PROJECT_NAME = BuildConfig.H5_PROJECT_NAME;//H5项目名称
    String BASE_URL = BuildConfig.BASE_URL;//基地址
    String H5_BASE_URL = BuildConfig.H5_BASE_URL + H5_PROJECT_NAME;//H5访问基地址
    String IMAGE_UPLOAD_URL = BuildConfig.IMAGE_UPLOAD_URL;//图片上传基地址

    /**
     * 用户登录
     *
     * @author Andy
     * created at 2019/3/14 0014 9:31
     */
    @POST(PROJECT_NAME + "/login/authentication/{accountNumber}")
    @Headers({"urlname:login"})
    Observable<LoginEntity> postAuthentication(@Path("accountNumber") String accountNumber, @QueryMap() Map<String, String> parameter);

}
