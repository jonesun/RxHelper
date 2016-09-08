package jone.helper.pagingload;

import jone.helper.BasePresenter;
import jone.helper.BaseView;

/**
 * Created by jone.sun on 2016/7/25.
 */

public interface PagingLoadControl {
    interface View extends BaseView<Presenter>{
        void showLoadingView();

        void showNoNetworkView();

        void showNoDataView();

        void showErrorView();

        void showDataListView();

        void hideLoadingView();
    }
    interface Presenter extends BasePresenter{

        void refresh();

        void loadMore();
    }
}
