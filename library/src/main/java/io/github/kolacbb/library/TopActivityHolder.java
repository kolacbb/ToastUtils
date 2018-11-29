package io.github.kolacbb.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class TopActivityHolder implements Application.ActivityLifecycleCallbacks {

    private Activity mActivity;

    private static TopActivityHolder sInstance;

    private TopActivityHolder() {

    }

    public static TopActivityHolder getInstance() {
        if (sInstance == null) {
            synchronized (TopActivityHolder.class) {
                if (sInstance == null) {
                    sInstance = new TopActivityHolder();
                }
            }
        }
        return sInstance;
    }

    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (mActivity == activity) {
            mActivity = null;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
