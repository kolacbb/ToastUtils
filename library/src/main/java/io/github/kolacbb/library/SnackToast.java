package io.github.kolacbb.library;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class SnackToast extends ToastImpl {

    private Context mCtx;
    private FrameLayout mRootView;

    public SnackToast(Context context) {
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
        if (mRootView != null) {
            Animation alphaAnimation = AnimationUtils.loadAnimation(mRootView.getContext(), R.anim.toast_exit);
            getView().startAnimation(alphaAnimation);
            mRootView.removeView(getView());
            mRootView = null;
        }
    }

    @Override
    public void handleShow() {
        Activity activity = TopActivityHolder.getInstance().getActivity();
        if (activity == null) {
            Log.e("Toaster", "Can't find activity show toast.");
            return;
        }

        mRootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);

        View view = getView();
        if (view == null) {
            view = createView(mCtx, getText(), mRootView);
            setView(view);
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = getGravity() | Gravity.CENTER;
        params.topMargin = getYOffset();
        params.bottomMargin = getYOffset();
        ViewGroup.LayoutParams oriParams = view.getLayoutParams();
        if (oriParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams oriFrameParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.leftMargin = oriFrameParams.leftMargin;
            params.rightMargin = oriFrameParams.rightMargin;
        }
        mRootView.addView(view, params);
        Animation alphaAnimation = AnimationUtils.loadAnimation(mCtx, R.anim.toast_enter);
        getView().startAnimation(alphaAnimation);
    }
}
