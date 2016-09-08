package jone.helper.network.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import jone.helper.entity.GankIoEntity;

/**
 * Created by jone.sun on 2016/6/21.
 */

public class GankIoApiResult {
    public boolean error;
    public @SerializedName("results") List<GankIoEntity> gankIoEntityList;
}
