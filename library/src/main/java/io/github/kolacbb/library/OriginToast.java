package io.github.kolacbb.library;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

public class OriginToast implements IToast {

    private Context mCtx;
    private ToastHandler mHandler;
    private ToastParam mToastParam;
    private  Toast mToast;

    public OriginToast(Context context, ToastHandler handler) {
        mCtx = context;
        mHandler = handler;
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
        mToast = new Toast(mCtx);
        ToastHooker.hookHandler(mToast);
        if (mToastParam.view == null) {
            mToastParam.view = ToastUtils.createView(mToastParam.text);
            mToast.setView(mToastParam.view);
        }
        mToast.setGravity(mToastParam.gravity, mToastParam.x, mToastParam.y);
        mToast.setDuration(mToastParam.duration);
        ToastHooker.hookDuration(mToast);
        mToast.show();
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
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
