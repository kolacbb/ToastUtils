package io.github.kolacbb.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class OverLayToast extends ToastImpl {

    private Context mCtx;
    private WindowManager mWindowManager;

    public OverLayToast(Context context) {
        super(context);
        mCtx = context;
    }

    @Override
    public void show() {
        ToastHandler.getInstance().removeCallbacksAndMessages(this);
        ToastHandler.getInstance().sendMessage(Message.obtain(ToastHandler.getInstance(), ToastHandler.SHOW, this));
    }

    @Override
    public void cancel() {
        if (mWindowManager != null) {
            try {
                mWindowManager.removeView(getView());
                mWindowManager = null;
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void handleShow() {
        Activity activity = TopActivityHolder.getInstance().getActivity();
        if (activity == null) {
            Log.e("Toaster", "Can't find activity show toast.");
            return;
        }
        mWindowManager = activity.getWindowManager();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                SystemUtils.isDrawOverlaysEnabled(mCtx)) {
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

        params.windowAnimations = R.style.ToastAnimation;
        params.gravity = getGravity();
        params.x = getXOffset();
        params.y = getYOffset();

        View view = getView();
        if (view == null) {
            view = createView(mCtx, getText(), null);
            setView(view);
        }

        mWindowManager.addView(view, params);
    }
}
