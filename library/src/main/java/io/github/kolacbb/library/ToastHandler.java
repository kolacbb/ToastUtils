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

    private static ToastHandler sInstance;
    private ToastHandler(Looper looper) {
        super(looper);
    }

    public static ToastHandler getInstance() {
        if (sInstance == null) {
            synchronized (ToastHandler.class) {
                sInstance = new ToastHandler(Looper.getMainLooper());
            }
        }
        return sInstance;
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
