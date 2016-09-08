package jone.helper.network;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jone.helper.BuildConfig;
import jone.helper.network.api.BingPictureApi;
import jone.helper.network.api.GankIoApi;
import jone.helper.network.api.TnGouApi;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jone.sun on 2016/6/12.
 */
public class Network {

    private static TnGouApi tnGouApiInstance;
    private static BingPictureApi bngPictureApiInstance;
    private static GankIoApi gankIoApiInstance;
    private static OkHttpClient okHttpClientInstance;

    public static TnGouApi getTnGouApiInstance() {
        if (tnGouApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TnGouApi.BASE_URL)
                    .client(getOkHttpClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            tnGouApiInstance = retrofit.create(TnGouApi.class);
        }
        return tnGouApiInstance;
    }

    public static BingPictureApi getBngPictureApiInstance() {
        if (bngPictureApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BingPictureApi.BASE_URL)
                    .client(getOkHttpClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            bngPictureApiInstance = retrofit.create(BingPictureApi.class);
        }
        return bngPictureApiInstance;
    }

    public static GankIoApi getGankIoApiInstance() {
        if (gankIoApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GankIoApi.BASE_URL)
                    .client(getOkHttpClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankIoApiInstance = retrofit.create(GankIoApi.class);
        }
        return gankIoApiInstance;
    }

    private static OkHttpClient getOkHttpClientInstance() {
        if (okHttpClientInstance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("RxHelperHttpLog", message));
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            //网络缓存路径文件
            // File httpCacheDirectory = new File(BaseApplication.getInstance().getExternalCacheDir(), "responses");
            okHttpClientInstance = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
            //build.cache(new Cache(getCacheFile(context), CACHE_SIZE));
        }
        return okHttpClientInstance;
    }
}
