package com.yunhuakeji.librarybase.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yunhuakeji.librarybase.base.BaseApplication;
import com.yunhuakeji.librarybase.util.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zsp on 2017/4/1.
 * 网络
 */

public class IdeaApi {
    private Retrofit retrofit;
    private IdeaApiService service;

    private IdeaApi() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            try {
                String text = URLDecoder.decode(message, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(IdeaApiService.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(IdeaApiService.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(IdeaApiService.BASE_URL)
                .build();
        service = retrofit.create(IdeaApiService.class);
    }

    //  创建单例
    private static class SingletonHolder {
        private static final IdeaApi INSTANCE = new IdeaApi();
    }
    public static synchronized IdeaApiService getApiService() {
        return SingletonHolder.INSTANCE.service;
    }

    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtil.isNetworkAvailable()) {  //没网强制从缓存读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }


            Response originalResponse = chain.proceed(request);
            if (NetworkUtil.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }
}
