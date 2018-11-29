package io.github.kolacbb.library;

import android.app.Application;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    private static final int MAX_SHORT_LENGTH = 20;
    private static final int DEFAULT_Y = 24;
    private static int sDefaultY;
    private static Application sApplication;
    private static ToastHandler sHandler = new ToastHandler(Looper.getMainLooper());

    private ToastUtils() {
    }

    public static void init(Application application) {
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(TopActivityHolder.getInstance());
        sDefaultY = SystemUtils.dp2px(application, DEFAULT_Y);
    }

    public static void show(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        make(text).show();
    }

    public static void showMiddle(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        ToastImpl toast = make(text);
        toast.setGravity(Gravity.CENTER, sDefaultY, sDefaultY);
        toast.show();
    }

    public static ToastImpl make(String text) {
        ToastImpl toast;
        if (SystemUtils.isNotificationEnabled(sApplication)) {
            toast = new OriginToast(sApplication, sHandler);
        } else if (SystemUtils.isDrawOverlaysEnabled(sApplication)) {
            toast = new OverLayToast(sHandler);
        } else {
            toast = new SnackToast(sHandler);
        }

        toast.setText(text);
        toast.setGravity(Gravity.BOTTOM, sDefaultY, sDefaultY);
        toast.setDuration(TextUtils.isEmpty(text) || text.length() <= MAX_SHORT_LENGTH ?
                Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        return toast;
    }


}
