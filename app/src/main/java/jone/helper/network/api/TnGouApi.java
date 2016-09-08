package jone.helper.network.api;

import java.util.List;

import jone.helper.entity.TnGouApiClassify;
import jone.helper.entity.TnGouApiGallery;
import jone.helper.entity.TnGouPicture;
import jone.helper.network.result.TnGouApiResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 天狗API
 * Created by jone.sun on 2016/6/12.
 */
public interface TnGouApi {

    String BASE_URL = "http://www.tngou.net/tnfs/api/";

    //http://www.tngou.net/tnfs/api/classify
    @GET("classify")
    Observable<TnGouApiResult<TnGouApiClassify>> classify();

    //"http://www.tngou.net/tnfs/api/list?page=" + pageIndex + "&id=" + clazzId
    @GET("list")
    Observable<TnGouApiResult<TnGouApiGallery>> galleryList(@Query("page") int page, @Query("id") String classId);

    //"http://www.tngou.net/tnfs/api/show?id=" + showId
    @GET("show")
    Observable<List<TnGouPicture>> pictureList(@Query("id") String showId);
}
