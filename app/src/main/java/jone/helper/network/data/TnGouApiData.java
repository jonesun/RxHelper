package jone.helper.network.data;

import java.util.List;

import jone.helper.entity.TnGouApiClassify;
import jone.helper.entity.TnGouApiGallery;
import jone.helper.entity.TnGouPicture;
import jone.helper.network.Network;
import jone.helper.network.result.TnGouApiResult;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jone.sun on 2016/6/12.
 */
public class TnGouApiData {
    private static TnGouApiData ourInstance = new TnGouApiData();

    public static TnGouApiData getInstance() {
        return ourInstance;
    }

    private TnGouApiData() {
    }


    public void loadClassify(Subscriber<List<TnGouApiClassify>> subscriber) {
        Network.getTnGouApiInstance().classify()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new HttpResultFunc1<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void loadGalleryList(Subscriber<TnGouApiGallery> subscriber, int page, String classId) {
        Network.getTnGouApiInstance().galleryList(page, classId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new HttpResultFunc1<>())
                .flatMap((Func1<List<TnGouApiGallery>, Observable<TnGouApiGallery>>) Observable::from)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void loadPictureList(Subscriber<List<TnGouPicture>> subscriber, String showId) {
        Network.getTnGouApiInstance().pictureList(showId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public class HttpResultFunc1<T> implements Func1<TnGouApiResult<T>, List<T>> {

        @Override
        public List<T> call(TnGouApiResult<T> result) {
            if (result.isStatus()) {
                return result.getTngou();
            }
            throw new ApiException(result.isStatus());
        }
    }

    public static class ApiException extends RuntimeException {

        public ApiException(boolean status) {
            this(getApiExceptionMessage(status));
        }

        public ApiException(String detailMessage) {
            super(detailMessage);
        }

        /**
         * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
         * 需要根据错误码对错误信息进行一个转换，再显示给用户
         *
         * @param status
         * @return
         */
        private static String getApiExceptionMessage(boolean status) {
            String message;
            if (status) {
                message = "成功返回数据";
            } else {
                message = "没有数据";
            }
            return message;
        }
    }
}
