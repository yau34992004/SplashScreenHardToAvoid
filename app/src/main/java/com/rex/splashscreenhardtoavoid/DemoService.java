package com.rex.splashscreenhardtoavoid;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by rex.yau on 6/22/2015.
 */
public class DemoService extends IntentService {

    public static final String TAG = "DemoService";

    public static void startToGetAllow(Context context) {
        context.startService(new Intent(context, DemoService.class));
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DemoService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "checkServiceStarted");
        //for demo only , just wait
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "checkServiceEnd");
        ((DemoApplication) getApplication()).setAllow(true);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(SplashScreenActivity.FILTER));
    }

}
