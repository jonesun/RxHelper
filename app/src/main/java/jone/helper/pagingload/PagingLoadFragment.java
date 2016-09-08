package jone.helper.pagingload;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import jone.helper.R;

/**
 * Created by jone.sun on 2016/7/25.
 */

public class PagingLoadFragment extends Fragment implements PagingLoadControl.View, SwipeRefreshLayout.OnRefreshListener{
    private PagingLoadControl.Presenter mPresenter;

    @Override
    public void setPresenter(PagingLoadControl.Presenter presenter) {
        mPresenter = presenter;
    }

    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private TextView txtEmpty;
    private LinearLayout layout_no_network;
    private ProgressBar progress_bar;
    private boolean hasRootView = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_paging_load, container, false);
            hasRootView = false;
        } else {
            hasRootView = true;
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshWidget = findViewWithGeneric(view, R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
//            mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
//                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                            .getDisplayMetrics()));
        txtEmpty = findViewWithGeneric(view, android.R.id.empty);
        progress_bar = findViewWithGeneric(view, R.id.progress_bar);
        mRecyclerView = findViewWithGeneric(view, android.R.id.list);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPresenter != null){
            mPresenter.unsubscribe();
        }
    }

    public <T extends View> T findViewWithGeneric(View view, int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showNoNetworkView() {

    }

    @Override
    public void showNoDataView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showDataListView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
