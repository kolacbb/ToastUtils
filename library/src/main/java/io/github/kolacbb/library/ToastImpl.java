package io.github.kolacbb.library;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class ToastImpl {

    public static final int DURATION_SHORT = 2000;
    public static final int DURATION_LONG = 4000;

    private int mDuration;
    private View mNextView;
    private CharSequence mText;
    private int mGravity = Gravity.BOTTOM;
    private int mX = 0;
    private int mY;

    public ToastImpl(Context context) {
        mY = context.getResources().getDimensionPixelSize(R.dimen.toast_y_offset);
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public View getView() {
        return mNextView;
    }

    public void setView(View view) {
        mNextView = view;
    }

    public CharSequence getText() {
        return mText;
    }

    public void setText(CharSequence text) {
        mText = text;
    }

    public int getGravity() {
        return mGravity;
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        mGravity = gravity;
        mX = xOffset;
        mY = yOffset;
    }

    public int getXOffset() {
        return mX;
    }

    public int getYOffset() {
        return mY;
    }

    public static View createView(Context context, CharSequence text, ViewGroup root) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tv = (TextView) inflate.inflate(R.layout.view_toast, root, false);
        tv.setText(text);
        return tv;
    }

    public abstract void show();

    public abstract void handleShow();

    public abstract void cancel();
}
