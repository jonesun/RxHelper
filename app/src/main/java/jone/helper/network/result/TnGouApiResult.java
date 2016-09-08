package jone.helper.network.result;

import java.util.List;

/**
 * Created by jone.sun on 2016/6/12.
 */
public class TnGouApiResult<T> {
    private boolean status;
    private List<T> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<T> getTngou() {
        return tngou;
    }

    public void setTngou(List<T> tngou) {
        this.tngou = tngou;
    }
}
