package io.github.kolacbb.toaster;

import android.app.Application;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import io.github.kolacbb.library.ToastImpl;
import io.github.kolacbb.library.Toaster;

public class ToastUtils {

    private static Application sApplication;

    public static void init(Application application) {
        sApplication = application;
        Toaster.init(application);
    }

    public static void show(CharSequence text) {
        Toaster.show(text);
    }

    public static void showMiddle(CharSequence text) {
        Toaster.showMiddle(text);
    }

    public static void showBanner(CharSequence text) {
        ToastImpl toast = new Toaster.Builder()
                .setGravity(Gravity.TOP)
                .setX(0)
                .setY(getActionBarHeight())
                .build();
        toast.setDuration(getDuration(text));
        View view = LayoutInflater.from(sApplication).inflate(R.layout.view_toast_banner, null);
        TextView tv = view.findViewById(R.id.message);
        ViewGroup.LayoutParams params = tv.getLayoutParams();
        params.width = getScreenWidth();
        tv.setLayoutParams(params);
        tv.setText(text);
//        toast.setGravity(Gravity.TOP, 0, getActionBarHeight());
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

    private static int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (sApplication.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, sApplication.getResources().getDisplayMetrics());
        }
        return 0;
    }

}
