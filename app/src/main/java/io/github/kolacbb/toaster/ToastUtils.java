package io.github.kolacbb.toaster;

import android.app.Application;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import io.github.kolacbb.library.ToastImpl;
import io.github.kolacbb.library.Toaster;
import io.github.kolacbb.library.TopActivityHolder;

public class ToastUtils {

    private static Toaster.Config mNormalConfig;
    private static Toaster.Config mMiddleConfig;
    private static Application sApplication;

    public static void init(Application application) {
        sApplication = application;
        Toaster.init(application);
        Toaster.sBackgroundColor = Color.parseColor("#E6666666");
        Toaster.sTextColor = Color.parseColor("#DEFEFEFE");

    }

    public static void show(CharSequence text) {
        if (mNormalConfig == null) {
            mNormalConfig = new Toaster.Config();
        }
        mNormalConfig.setDuration(getDuration(text));
        Toaster.show(text);
    }

    public static void showMiddle(CharSequence text) {
        if (mMiddleConfig == null) {
            mMiddleConfig = new Toaster.Config();
            mMiddleConfig.setGravity(Gravity.CENTER);
        }
        mMiddleConfig.setText(text);
        mMiddleConfig.setDuration(getDuration(text));
        Toaster.show(mMiddleConfig);
    }

    public static void showBanner(CharSequence text) {
        ToastImpl toast = Toaster.make();
        toast.setDuration(getDuration(text));
        View view = LayoutInflater.from(sApplication).inflate(R.layout.view_toast_banner, null);
        TextView tv = view.findViewById(R.id.message);
        ViewGroup.LayoutParams params = tv.getLayoutParams();
        params.width = getScreenWidth();
        tv.setLayoutParams(params);
        tv.setText(text);
        toast.setGravity(Gravity.TOP, 0, getActionBarHeight());
        toast.setView(view);
        toast.show();
    }

    private static int getDuration(CharSequence text) {
        return text != null && text.length() < 20 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
    }

    private static int getScreenWidth() {
        return sApplication.getResources().getDisplayMetrics().widthPixels;
    }

    private static int getStatusBarHeight() {
        int result = 25;
        int resourceId = sApplication.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = sApplication.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
