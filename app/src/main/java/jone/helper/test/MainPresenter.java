package jone.helper.test;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jone.sun on 2016/9/8.
 */

public class MainPresenter {

    MainView mainView;
    TaskManager taskData;

    public MainPresenter() {
        this.taskData = new TaskManager(new TaskDataSourceImpl());
    }

    public MainPresenter test() {
        this.taskData = new TaskManager(new TaskDataSourceTestImpl());
        return this;
    }

    public MainPresenter addTaskListener(MainView viewListener) {
        this.mainView = viewListener;
        return this;
    }

    public void getString() {
        Func1<String,String> dataAction = param -> taskData.getShowContent();
        Action1<String> viewAction = str -> mainView.onShowString(str);
        Observable.just("")
                .observeOn(Schedulers.io())
                .map(dataAction)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewAction, getDefaultErrorAction());

    }

    private Action1<Throwable> getDefaultErrorAction(){
        return Throwable::printStackTrace;
    }
}
