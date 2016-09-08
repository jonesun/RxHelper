package jone.helper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jone.helper.R;
import jone.helper.entity.GankIoEntity;
import jone.helper.network.data.GankIoApiData;
import jone.helper.ui.PictureRecyclerViewAdapter;
import rx.Subscriber;

/**
 * Created by jone.sun on 2016/9/8.
 */

public class GalleryTabContentFragment extends Fragment {
    private static final String TAG = GalleryTabContentFragment.class.getSimpleName();
    private static final String ARG_TYPE = "type";
    private PictureRecyclerViewAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    public static GalleryTabContentFragment newInstance(String type) {
        GalleryTabContentFragment fragment = new GalleryTabContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_tab_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshWidget = ((SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_widget));
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        mRecyclerView = ((RecyclerView)view.findViewById(android.R.id.list));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new PictureRecyclerViewAdapter(getContext());
        mRecyclerView.setAdapter(this.adapter);
        GankIoApiData.getInstance().loadBeautiesData(getArguments().getString("type"), new Subscriber<GankIoEntity>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "loadBeautiesData>>>onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "loadBeautiesData>>>onError", e);
            }

            @Override
            public void onNext(GankIoEntity gankIoEntity) {
                Log.e(TAG, "loadBeautiesData>>>onNext: " + gankIoEntity.getDesc());
                adapter.addData(gankIoEntity);
            }
        });
    }

}
