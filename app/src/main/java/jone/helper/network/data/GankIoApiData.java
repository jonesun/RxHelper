package jone.helper.network.data;

import android.content.Context;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jone.helper.R;
import jone.helper.entity.GankIoEntity;
import jone.helper.network.Network;
import jone.helper.network.result.GankIoApiResult;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jone.sun on 2016/6/21.
 */
public class GankIoApiData {
    private static GankIoApiData ourInstance = new GankIoApiData();

    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.CHINA);
    SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.CHINA);

    public static GankIoApiData getInstance() {
        return ourInstance;
    }

    private GankIoApiData() {
    }

    public void loadBeautiesData(String type, Subscriber<GankIoEntity> subscriber){
        Network.getGankIoApiInstance()
                .loadData(type, 100, 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<GankIoApiResult, Observable<GankIoEntity>>() {
                    @Override
                    public Observable<GankIoEntity> call(GankIoApiResult gankIoApiResult) {
                        return Observable.from(gankIoApiResult.gankIoEntityList);
                    }
                })
                .map(gankIoEntity -> {
                    try {
                        Date date = inputFormat.parse(gankIoEntity.getCreatedAt());
                        gankIoEntity.setCreatedAt(outputFormat.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        gankIoEntity.setCreatedAt("unknown date");
                    }
                    return gankIoEntity;
                })

                .subscribe(subscriber);

    }

//    public void loadBeautiesData(Context context, Subscriber<List<GankIoEntity>> subscriber){
//        Network.getGankIoApiInstance().loadData(context.getResources().getStringArray(R.array.gankIoTypes)[0], 100, 1)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(gankIoApiResult -> {
//                    List<GankIoEntity> gankIoEntityList = gankIoApiResult.gankIoEntityList;
//                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.CHINA);
//                    SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.CHINA);
//                    for (GankIoEntity gankIoEntity : gankIoEntityList) {
//                        try {
//                            Date date = inputFormat.parse(gankIoEntity.getCreatedAt());
//                            gankIoEntity.setCreatedAt(outputFormat.format(date));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            gankIoEntity.setCreatedAt("unknown date");
//                        }
//                    }
//                    return gankIoEntityList;
//                })
//                .subscribe(subscriber);
//    }
}
