package io.github.kolacbb.library;

import android.app.Application;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    private static final int MAX_SHORT_LENGTH = 20;
    private static Application sApplication;

    private ToastUtils() {
    }

    public static void init(Application application) {
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(TopActivityHolder.getInstance());
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
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static ToastImpl make(String text) {
        ToastImpl toast;
        if (SystemUtils.isNotificationEnabled(sApplication)) {
            toast = new OriginToast(sApplication);
        } else if (SystemUtils.isDrawOverlaysEnabled(sApplication)) {
            toast = new OverLayToast(sApplication);
        } else {
            toast = new SnackToast(sApplication);
        }

        toast.setText(text);
        toast.setDuration(TextUtils.isEmpty(text) || text.length() <= MAX_SHORT_LENGTH ?
                Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        return toast;
    }


}
