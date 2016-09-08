package jone.helper.network.api;

import jone.helper.network.result.GankIoApiResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 请求个数： 数字，大于0
 第几页：数字，大于0
 http://mm.ziliao.link/pic/0Q64F32013.html
 * Created by jone.sun on 2016/6/21.
 */

public interface GankIoApi {
    String BASE_URL = "http://gank.io/api/data/"; // 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all

    @GET("{type}/{count}/{page}")
    Observable<GankIoApiResult> loadData(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
