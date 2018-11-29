package io.github.kolacbb.library;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public abstract class ToastImpl {

    public static final int DURATION_SHORT = 2000;
    public static final int DURATION_LONG = 4000;

    private int mDuration;
    private View mNextView;
    private String mText;
    private int mGravity = Gravity.BOTTOM;
    private int mX = 0;
    private int mY = 0;

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public View getView() {
        return mNextView;
    }

    public void setView(View nextView) {
        mNextView = nextView;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
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

    public View createView(Context context, String text) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tv = (TextView) inflate.inflate(R.layout.view_toast, null);
        tv.setText(text);
        return tv;
    }

    abstract void show();

    abstract void handleShow();

    abstract void cancel();
}
