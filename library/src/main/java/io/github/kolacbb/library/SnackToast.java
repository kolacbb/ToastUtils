package io.github.kolacbb.library;

import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


public class SnackToast implements IToast, Animation.AnimationListener {

    private ToastHandler mHandler;
    private TopActivityHolder mHolder;
    private ToastParam mToastParam;
    private FrameLayout mRootView;

    public SnackToast(TopActivityHolder holder, ToastHandler handler) {
        mHolder = holder;
        mHandler = handler;
    }

    @Override
    public int getDuration() {
        return mToastParam.duration;
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
            mToastParam.view.startAnimation(alphaAnimation);
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
        mRootView = mHolder.getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = mToastParam.gravity | Gravity.CENTER;
        params.topMargin = mToastParam.y;
        params.bottomMargin = mToastParam.y;
        params.leftMargin = mToastParam.x;
        params.rightMargin = mToastParam.x;
        if (mToastParam.view == null) {
            mToastParam.view = ToastUtils.createView(mToastParam.text);
        }
        mRootView.addView(mToastParam.view, params);
        Animation alphaAnimation = AnimationUtils.loadAnimation(mRootView.getContext(), R.anim.toast_enter);
        mToastParam.view.startAnimation(alphaAnimation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mRootView.removeView(mToastParam.view);
        mRootView = null;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
