package io.github.kolacbb.library;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

class ToastHooker {
    private static Field sField_TN;
    private static Field sField_TN_Handler;
    private static Field sField_TN_Duration;

    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
            sField_TN_Duration = sField_TN.getType().getDeclaredField("mDuration");
            sField_TN_Duration.setAccessible(true);
        } catch (Exception e) {

        }
    }

    static void hookHandler(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new SafelyHandlerWrapper(preHandler));
        } catch (Exception e) {
            Log.e("Toaster", "inject handler failed");
        }
    }

    static void hookDuration(Toast toast) {
        if (toast.getDuration() == Toast.LENGTH_LONG
                && (Build.VERSION.SDK_INT == Build.VERSION_CODES.N || Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1)) {
            try {
                Object tn = sField_TN.get(toast);
                sField_TN_Duration.set(tn, Toast.LENGTH_SHORT);
            } catch (Exception e) {
                Log.e("Toaster", "inject duration failed");
            }
        }
    }
}
