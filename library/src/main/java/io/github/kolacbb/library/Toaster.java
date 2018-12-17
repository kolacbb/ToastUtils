package io.github.kolacbb.library;

import android.app.Application;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class Toaster {

    public static int sBackgroundColor = Color.parseColor("#E6EEEEEE");
    public static int sTextSize = 14;
    public static int sTextColor = Color.parseColor("#DE000000");
    public static Typeface sTypeFace = Typeface.create("sans-serif", Typeface.NORMAL);
    public static int sGravity = Gravity.BOTTOM;
    public static int sDuration = Toast.LENGTH_SHORT;

    private static Application sApplication;

    private Toaster() {
    }

    public static void init(Application application) {
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(TopActivityHolder.getInstance());
    }

    public static void show(CharSequence text) {
        Toaster.show(new Config().setText(text));
    }

    public static void show(Config config) {
        if (TextUtils.isEmpty(config.getText())) {
            return;
        }

        ToastImpl toast;
        if (SystemUtils.isNotificationEnabled(sApplication)) {
            toast = new OriginToast(sApplication);
        } else if (SystemUtils.isDrawOverlaysEnabled(sApplication)) {
            toast = new OverLayToast(sApplication);
        } else {
            toast = new SnackToast(sApplication);
        }

        TextView view = (TextView) ToastImpl.createView(sApplication, config.getText(), null);

        Drawable drawable = view.getBackground();
        drawable.setColorFilter(config.getBackgroundColor(), PorterDuff.Mode.SRC_IN);
        view.setText(config.getText());
        view.setTextColor(config.getTextColor());
        view.setTextSize(config.getTextSize());
        view.setTypeface(config.getTypeFace());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }


        toast.setView(view);
        toast.setDuration(config.getDuration());
        toast.show();
    }

    public static ToastImpl make() {
        ToastImpl toast;
        if (SystemUtils.isNotificationEnabled(sApplication)) {
            toast = new OriginToast(sApplication);
        } else {
            toast = new OverLayToast(sApplication);
        }
        return toast;
    }


    public static class Config {
        private int mBackgroundColor = sBackgroundColor;
        private int mTextSize = sTextSize;
        private int mTextColor = sTextColor;
        private int mGravity = sGravity;
        private int mDuration = sDuration;
        private Typeface mTypeFace = sTypeFace;

        private CharSequence mText;

        public int getBackgroundColor() {
            return mBackgroundColor;
        }

        public void setBackgroundColor(int backgroundColor) {
            mBackgroundColor = backgroundColor;
        }

        public int getTextSize() {
            return mTextSize;
        }

        public Config setTextSize(int textSize) {
            mTextSize = textSize;
            return this;
        }

        public int getTextColor() {
            return mTextColor;
        }

        public Config setTextColor(int textColor) {
            mTextColor = textColor;
            return this;
        }

        public int getGravity() {
            return mGravity;
        }

        public Config setGravity(int gravity) {
            mGravity = gravity;
            return this;
        }

        public int getDuration() {
            return mDuration;
        }

        public Config setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Typeface getTypeFace() {
            return mTypeFace;
        }

        public Config setTypeFace(Typeface typeFace) {
            mTypeFace = typeFace;
            return this;
        }

        public CharSequence getText() {
            return mText;
        }

        public Config setText(CharSequence text) {
            mText = text;
            return this;
        }
    }

}
