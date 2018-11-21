package io.github.kolacbb.library;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

public class OverLayToast implements IToast {

    private TopActivityHolder mHolder;
    private WindowManager mWindowManager;
    private ToastParam mToastParam = null;

    public OverLayToast(TopActivityHolder holder) {
        mHolder = holder;
    }

    @Override
    public void show() {
        if (mToastParam == null) {
            mToastParam = new ToastParam();
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                params.type = WindowManager.LayoutParams.TYPE_PHONE;
            }
        }
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        params.gravity = mToastParam.gravity;
        params.x = mToastParam.x;
        params.y = mToastParam.y;


        mWindowManager = mHolder.getActivity().getWindowManager();
        mWindowManager.addView(mToastParam.view, params);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancel();
            }
        }, mToastParam.duration == Toast.LENGTH_SHORT ? ToastParam.DURATION_SHORT : ToastParam.DURATION_LONG);
    }

    @Override
    public void cancel() {
        if (mWindowManager != null) {
            mWindowManager.removeView(mToastParam.view);
        }
    }

    @Override
    public void apply(ToastParam param) {
        mToastParam = param;
    }
}
