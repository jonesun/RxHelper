package jone.helper.network.result;

import java.util.List;

import jone.helper.entity.BingPicture;

/**
 * Created by jone.sun on 2016/6/21.
 */

public class BingApiResult {
    private List<BingPicture> images;

    public List<BingPicture> getImages() {
        return images;
    }

    public void setImages(List<BingPicture> images) {
        this.images = images;
    }
}
