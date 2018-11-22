package io.github.kolacbb.library;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

    private static final int MAX_SHORT_LENGTH = 20;
    private static final int DEFAULT_Y = 24;
    private static int sDefaultY;
    private static Application sApplication;
    private static TopActivityHolder mActivityHolder;
    private static ToastHandler sHandler = new ToastHandler(Looper.getMainLooper());

    private ToastUtils() {
    }

    public static void init(Application application) {
        sApplication = application;
        mActivityHolder = new TopActivityHolder();
        sApplication.registerActivityLifecycleCallbacks(mActivityHolder);
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

        IToast toast = make(text);
        ToastParam param = toast.getParam();
        param.gravity = Gravity.CENTER;
        toast.setParam(param);
        toast.show();
    }

    public static IToast make(String text) {
        IToast toast;
        if (SystemUtils.isNotificationEnabled(sApplication)) {
            toast = new OriginToast(sApplication, sHandler);
        } else if (SystemUtils.isDrawOverlaysEnabled(sApplication)) {
            toast = new OverLayToast(mActivityHolder, sHandler);
        } else {
            toast = new SnackToast(mActivityHolder, sHandler);
        }

        ToastParam param = new ToastParam();
        param.text = text;
        param.y = sDefaultY;
        param.duration = TextUtils.isEmpty(text) || text.length() <= MAX_SHORT_LENGTH ?
                Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
        toast.setParam(param);
        return toast;
    }

    static View createView(String text) {
        LayoutInflater inflate = (LayoutInflater)
                sApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tv = (TextView) inflate.inflate(R.layout.view_toast, null);
        tv.setText(text);
        return tv;
    }


}
