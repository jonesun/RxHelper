package jone.helper.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jone.helper.R;
import jone.helper.entity.GankIoEntity;

import static java.security.AccessController.getContext;

/**
 * Created by jone.sun on 2016/9/7.
 */

public class PictureRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GankIoEntity> dataList = new ArrayList<>();
    private Context context;

    public PictureRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PictureItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PictureItemViewHolder) {
            GankIoEntity data = dataList.get(position);
            Log.e("ss", "data: " + data.getDesc());
            PictureItemViewHolder viewHolder = (PictureItemViewHolder) holder;
            viewHolder.mTitle.setText(data.getDesc() + "");
            Glide.with(context)
                    .load(data.getUrl())
                    .placeholder(R.mipmap.ic_image_loading) // can also be a drawable
                    .error(R.mipmap.ic_image_loadfail) // will be displayed if the image cannot be loaded
                    .into(viewHolder.mImage);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(GankIoEntity data) {
        dataList.add(data);
        notifyItemInserted(dataList.size() - 1);
    }

    public class PictureItemViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public ImageView mImage;

        public PictureItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mImage = (ImageView) v.findViewById(R.id.ivImage);
        }
    }
}
