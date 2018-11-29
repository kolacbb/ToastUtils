package io.github.kolacbb.library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastHandler extends Handler {

    public static final int SHOW = 0;
    public static final int CANCEL = 1;
    private WeakReference<ToastImpl> mPreToast;

    public ToastHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW: {
                if (mPreToast != null) {
                    ToastImpl toast = mPreToast.get();
                    if (toast != null) {
                        toast.cancel();
                    }
                }
                ToastImpl toast = (ToastImpl) msg.obj;
                toast.handleShow();
                mPreToast = new WeakReference<>(toast);
                if (!(toast instanceof OriginToast)) {
                    Message m = obtainMessage();
                    m.what = CANCEL;
                    m.obj = toast;
                    sendMessageDelayed(m, toast.getDuration() == Toast.LENGTH_LONG ?
                            ToastImpl.DURATION_LONG : ToastImpl.DURATION_SHORT);
                }

                break;
            }
            case CANCEL: {
                ToastImpl toast = (ToastImpl) msg.obj;
                toast.cancel();
                break;
            }
        }

    }
}
