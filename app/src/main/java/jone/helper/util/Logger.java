package jone.helper.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.util.function.Supplier;

import jone.helper.BuildConfig;

/**
 * Created by jone.sun on 2016/7/25.
 */
public class Logger {
    private static Logger ourInstance = new Logger();

    public static Logger getInstance() {
        return ourInstance;
    }

    private boolean debug = BuildConfig.DEBUG;

    private Logger() {
    }

    public void debug(String tag, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            debug(tag, () -> message);
        } else {
            androidLoggerDebug(tag, message);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void debug(String tag, Supplier<String> message) {
        androidLoggerDebug(tag, message.get());
    }

    private void androidLoggerDebug(String tag, String message){
        if (debug) {
            Log.d(tag, message);
        }
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
