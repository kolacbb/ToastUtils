package io.github.kolacbb.library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

public class OriginToast extends ToastImpl {

    private Context mCtx;
    /*
     * Use single Toast instance on lower Android P.
     */
    private static Toast mToast;

    public OriginToast(Application context) {
        super(context);
        mCtx = context;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void handleShow() {
        if (mToast == null) {
            mToast = new Toast(mCtx);
            ToastHooker.hookHandler(mToast);
        }
        View view = getView();
        if (view == null) {
            view = createView(mCtx, getText(), null);
            setView(view);
        }
        mToast.setView(view);
        mToast.setGravity(getGravity(), getXOffset(), getYOffset());
        mToast.setDuration(getDuration());
        ToastHooker.hookDuration(mToast);
        mToast.show();
    }

    @Override
    public void show() {
        ToastHandler.getInstance().removeCallbacksAndMessages(this);
        ToastHandler.getInstance().sendMessage(Message.obtain(ToastHandler.getInstance(), ToastHandler.SHOW, this));
    }

    @Override
    public void cancel() {
        if (mToast != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mToast.cancel();
            mToast = null;
        }
    }
}
