package io.github.kolacbb.library;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Message;
import android.view.WindowManager;

public class OverLayToast implements IToast {

    private ToastHandler mHandler;
    private TopActivityHolder mHolder;
    private WindowManager mWindowManager;
    private ToastParam mToastParam = null;

    public OverLayToast(TopActivityHolder holder, ToastHandler handler) {
        mHolder = holder;
        mHandler = handler;
    }

    @Override
    public int getDuration() {
        return mToastParam.duration;
    }

    @Override
    public void show() {
        Message msg = mHandler.obtainMessage();
        msg.what = ToastHandler.SHOW;
        msg.obj = this;
        mHandler.sendMessage(msg);
    }

    @Override
    public void cancel() {
        if (mWindowManager != null) {
            mWindowManager.removeView(mToastParam.view);
            mWindowManager = null;
        }
    }

    @Override
    public void setParam(ToastParam param) {
        mToastParam = param;
    }

    @Override
    public ToastParam getParam() {
        return mToastParam;
    }

    @Override
    public void handleShow() {
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

        if (mToastParam.view == null) {
            mToastParam.view = ToastUtils.createView(mToastParam.text);
        }

        mWindowManager.addView(mToastParam.view, params);
    }
}
