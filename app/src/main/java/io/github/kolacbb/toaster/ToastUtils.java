package io.github.kolacbb.toaster;

import android.app.Application;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;
import io.github.kolacbb.library.Toaster;

public class ToastUtils {

    private static Toaster.Config mNormalConfig;
    private static Toaster.Config mMiddleConfig;

    public static void init(Application application) {
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

    private static int getDuration(CharSequence text) {
        return text != null && text.length() < 20 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
    }
}
