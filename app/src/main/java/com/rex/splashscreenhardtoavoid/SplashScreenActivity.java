package com.rex.splashscreenhardtoavoid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by rex.yau on 6/22/2015.
 */
public class SplashScreenActivity extends Activity {

    public static final String FILTER = "com.rex.splash.screen.COMPLETE";
    private static final String TAG = "SplashScreenActivity";

    private MyBroadcastReceiver mMyBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyBroadcastReceiver = new MyBroadcastReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMyBroadcastReceiver, new IntentFilter(FILTER));

        if (getDemoApplication().isAllow()) {
            MainActivity.startMainActivity(this);
        } else {
            Log.d(TAG, "checkStartDoSomethingToGetAllow");
            getDemoApplication().doSomethingToGetAllow();
        }

    }

    public void weCanGoNow() {
        Log.d(TAG, "gotThatWeCanGoNow");
        MainActivity.startMainActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMyBroadcastReceiver);
    }

    private DemoApplication getDemoApplication() {
        return (DemoApplication) getApplication();
    }

    private static class MyBroadcastReceiver extends BroadcastReceiver {

        private WeakReference<SplashScreenActivity> mMainActivityWeakReference;

        public MyBroadcastReceiver(SplashScreenActivity activity) {
            mMainActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "checkReceiveIntent");
            SplashScreenActivity splashScreenActivity = mMainActivityWeakReference.get();
            if (splashScreenActivity != null) {
                splashScreenActivity.weCanGoNow();
            }
        }
    }
}
