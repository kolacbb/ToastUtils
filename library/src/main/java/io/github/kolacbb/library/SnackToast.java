package io.github.kolacbb.library;

import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class SnackToast extends ToastImpl implements Animation.AnimationListener {

    private ToastHandler mHandler;
    private FrameLayout mRootView;

    public SnackToast(ToastHandler handler) {
        mHandler = handler;
    }

    @Override
    public void show() {
        mHandler.sendMessage(Message.obtain(mHandler, ToastHandler.SHOW, this));
    }

    @Override
    public void cancel() {
        if (mRootView != null) {
            Animation alphaAnimation = AnimationUtils.loadAnimation(mRootView.getContext(), R.anim.toast_exit);
            alphaAnimation.setAnimationListener(this);
            getView().startAnimation(alphaAnimation);
        }
    }

    @Override
    public void handleShow() {
        mRootView = TopActivityHolder.getInstance().getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = getGravity() | Gravity.CENTER;
        params.topMargin = getYOffset();
        params.bottomMargin = getYOffset();
        params.leftMargin = getXOffset();
        params.rightMargin = getXOffset();
        if (getView() == null) {
            setView(createView(mRootView.getContext(), getText()));
        }
        mRootView.addView(getView(), params);
        Animation alphaAnimation = AnimationUtils.loadAnimation(mRootView.getContext(), R.anim.toast_enter);
        getView().startAnimation(alphaAnimation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mRootView.removeView(getView());
        mRootView = null;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
