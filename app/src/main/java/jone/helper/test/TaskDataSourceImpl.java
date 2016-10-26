package jone.helper.test;

/**
 * Created by jone.sun on 2016/9/8.
 */

public class TaskDataSourceImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "Hello ";
    }

    @Override
    public String getStringFromCache() {
        return "World";
    }
}