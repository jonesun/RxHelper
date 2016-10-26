package jone.helper.test;

/**
 * Created by jone.sun on 2016/9/8.
 */

public class TaskManager {
    TaskDataSource dataSource;

    public TaskManager(TaskDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getShowContent() {
        //Todo what you want do on the original data
        return dataSource.getStringFromRemote() + dataSource.getStringFromCache();
    }
}
