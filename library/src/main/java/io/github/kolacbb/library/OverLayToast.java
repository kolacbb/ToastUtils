package io.github.kolacbb.library;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Message;
import android.view.WindowManager;

public class OverLayToast extends ToastImpl {

    private ToastHandler mHandler;
    private WindowManager mWindowManager;

    public OverLayToast(ToastHandler handler) {
        mHandler = handler;
    }

    @Override
    public void show() {
        mHandler.sendMessage(Message.obtain(mHandler, ToastHandler.SHOW, this));
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
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                SystemUtils.isDrawOverlaysEnabled(TopActivityHolder.getInstance().getActivity())) {
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

        mWindowManager = TopActivityHolder.getInstance().getActivity().getWindowManager();

        if (getView() == null) {
            setView(createView(TopActivityHolder.getInstance().getActivity(), getText()));
        }

        mWindowManager.addView(getView(), params);
    }
}
