package jone.helper.pagingload;

/**
 * Created by jone.sun on 2016/7/25.
 */

public class PagingLoadPresenter implements PagingLoadControl.Presenter {
    private PagingLoadControl.View view;
    public PagingLoadPresenter(PagingLoadControl.View view){
        this.view = view;
        view.setPresenter(this);
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }
}
