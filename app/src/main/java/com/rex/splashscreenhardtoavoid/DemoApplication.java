package com.rex.splashscreenhardtoavoid;

import android.app.Application;
import android.util.Log;

/**
 * Created by rex.yau on 6/22/2015.
 */
public class DemoApplication extends Application {


    private static final String TAG = "DemoApplication";

    private boolean mIsAllow;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public boolean isAllow() {
        return mIsAllow;
    }

    public void doSomethingToGetAllow() {
        DemoService.startToGetAllow(this);
    }

    public void setAllow(boolean isAllow) {
        Log.d(TAG, "checkIsAllow->" + isAllow);
        mIsAllow = isAllow;
    }
}
