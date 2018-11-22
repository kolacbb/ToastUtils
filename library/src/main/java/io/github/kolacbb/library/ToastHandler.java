package io.github.kolacbb.library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastHandler extends Handler {

    public static final int SHOW = 0;
    public static final int CANCEL = 1;
    private WeakReference<IToast> mPreToast;

    public ToastHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW: {
                if (mPreToast != null) {
                    IToast toast = mPreToast.get();
                    if (toast != null) {
                        toast.cancel();
                    }
                }
                IToast toast = (IToast) msg.obj;
                toast.show();
                mPreToast = new WeakReference<>(toast);
                if (!(toast instanceof OriginToast)) {
                    Message m = obtainMessage();
                    m.what = CANCEL;
                    m.obj = toast;
                    sendMessageDelayed(m, toast.getDuration() == Toast.LENGTH_LONG ?
                            ToastParam.DURATION_LONG : ToastParam.DURATION_SHORT);
                }

                break;
            }
            case CANCEL: {
                IToast toast = (IToast) msg.obj;
                toast.cancel();
                break;
            }
        }

    }
}
