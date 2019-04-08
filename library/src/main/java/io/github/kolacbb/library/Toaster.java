package io.github.kolacbb.library;

import android.app.Application;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Toaster {
    private static Application sApplication;

    private Toaster() {
    }

    public static void init(Application application) {
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(TopActivityHolder.getInstance());
    }

    public static void show(CharSequence text) {
        new Builder().setText(text).setDuration(getDuration(text)).show();
    }

    public static void showMiddle(CharSequence text) {
        new Builder().setText(text).setDuration(getDuration(text)).setGravity(Gravity.CENTER).show();
    }

    private static int getDuration(CharSequence text) {
        return text != null && text.length() < 20 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
    }

    public static class Builder {
        private CharSequence mText;
        private int mTextSize = 14;
        private int mTextColor = Color.parseColor("#DE000000");
        private Typeface mTypeFace = Typeface.create("sans-serif", Typeface.NORMAL);
        private int mBackgroundColor = Color.parseColor("#E6EEEEEE");
        private View mView = null;
        private int mGravity = Gravity.BOTTOM;
        private int mDuration = Toast.LENGTH_SHORT;
        private int mX = 0;
        private int mY = sApplication.getResources().getDimensionPixelSize(R.dimen.toast_y_offset);


        public CharSequence getText() {
            return mText;
        }

        public Builder setText(CharSequence text) {
            mText = text;
            return this;
        }

        public int getTextSize() {
            return mTextSize;
        }

        public Builder setTextSize(int textSize) {
            mTextSize = textSize;
            return this;
        }

        public int getTextColor() {
            return mTextColor;
        }

        public Builder setTextColor(int textColor) {
            mTextColor = textColor;
            return this;
        }

        public Typeface getTypeFace() {
            return mTypeFace;
        }

        public Builder setTypeFace(Typeface typeFace) {
            mTypeFace = typeFace;
            return this;
        }

        public int getBackgroundColor() {
            return mBackgroundColor;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            mBackgroundColor = backgroundColor;
            return this;
        }

        public View getView() {
            return mView;
        }

        public Builder setView(View view) {
            mView = view;
            return this;
        }

        public int getGravity() {
            return mGravity;
        }

        public Builder setGravity(int gravity) {
            mGravity = gravity;
            return this;
        }

        public int getDuration() {
            return mDuration;
        }

        public Builder setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public int getX() {
            return mX;
        }

        public Builder setX(int x) {
            mX = x;
            return this;
        }

        public int getY() {
            return mY;
        }

        public Builder setY(int y) {
            mY = y;
            return this;
        }

        public ToastImpl build() {
            ToastImpl toast;
            if (SystemUtils.isNotificationEnabled(sApplication)) {
                toast = new OriginToast(sApplication);
            } else {
                toast = new OverLayToast(sApplication);
            }

            View view = getView();
            if (view == null) {
                TextView tv = (TextView) ToastImpl.createView(sApplication, getText(), null);
                Drawable drawable = tv.getBackground();
                drawable.setColorFilter(getBackgroundColor(), PorterDuff.Mode.SRC_IN);
                tv.setText(getText());
                tv.setTextColor(getTextColor());
                tv.setTextSize(getTextSize());
                tv.setTypeface(getTypeFace());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tv.setBackground(drawable);
                } else {
                    tv.setBackgroundDrawable(drawable);
                }
                view = tv;
            }

            toast.setView(view);
            toast.setDuration(getDuration());
            toast.setGravity(getGravity(), mX, mY);
            return toast;
        }

        public void show() {
            build().show();
        }
    }

}
