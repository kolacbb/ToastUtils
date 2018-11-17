package io.github.kolacbb.library;

import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class SnackToast implements IToast {

    TopActivityHolder mHolder;

    private ToastParam mToastParam;
    private FrameLayout mRootView;
    WeakReference<FrameLayout> mReference;

    public SnackToast(TopActivityHolder holder) {
        mHolder = holder;
    }

    @Override
    public void show() {
        mRootView = mHolder.getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = mToastParam.gravity | Gravity.CENTER;
        params.topMargin = mToastParam.y;
        params.bottomMargin = mToastParam.y;
        params.leftMargin = mToastParam.x;
        params.rightMargin = mToastParam.x;
        mRootView.addView(mToastParam.view, params);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancel();
            }
        }, mToastParam.duration == Toast.LENGTH_SHORT ? ToastParam.DURATION_SHORT : ToastParam.DURATION_LONG);
    }

    @Override
    public void cancel() {
        if (mRootView != null) {
            mRootView.removeView(mToastParam.view);
        }
    }

    @Override
    public void apply(ToastParam param) {
        mToastParam = param;
    }
}
