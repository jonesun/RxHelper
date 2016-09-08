package jone.helper.network.data;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import jone.helper.entity.BingPicture;
import jone.helper.network.Network;
import jone.helper.network.result.BingApiResult;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jone.sun on 2016/6/20.
 */
public class BingApiData {
    private static BingApiData ourInstance = new BingApiData();

    public static BingApiData getInstance() {
        return ourInstance;
    }

    private BingApiData() {
    }
    
    public void loadData(Subscriber<BingPicture> subscriber){
        Network.getBngPictureApiInstance().getPictures("js", 0, 3, "zh-CN,")
                .map(BingApiResult::getImages)
                .concatMap((Func1<List<BingPicture>, Observable<BingPicture>>) Observable::from)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
