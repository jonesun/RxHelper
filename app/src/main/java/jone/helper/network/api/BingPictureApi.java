package jone.helper.network.api;

import java.util.List;

import jone.helper.entity.BingPicture;
import jone.helper.network.result.BingApiResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 必应图片API
 * /**http://www.bing.com/HPImageArchive.aspx?format=js&idx=1&n=1&mkt=en-US
 * format：接口返回格式，已知可选项xml,js
 * idx:日期表示0为当天，-1为明天，1为昨天，2为后天，依次类推，已知可选项-1 ~ 18
 * n: 个数
 * mkt:地区编号(可选项)，不同地区会获得不同壁纸。已知可选项en-US, zh-CN, ja-JP, en-AU, de-DE, en-NZ, en-CA
 * Created by jone.sun on 2016/6/13.
 */
public interface BingPictureApi {
    String BASE_URL = "http://www.bing.com/";

    @GET("HPImageArchive.aspx")
    Observable<BingApiResult> getPictures(@Query("format") String format, @Query("idx") int idx, @Query("n") int n, @Query("mkt") String mkt);
}
