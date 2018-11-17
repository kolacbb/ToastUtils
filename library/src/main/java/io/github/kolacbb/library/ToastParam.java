package io.github.kolacbb.library;

import android.view.Gravity;
import android.view.View;

public class ToastParam {
    public static final int DURATION_SHORT = 2000;
    public static final int DURATION_LONG = 4000;

    public View view;
    public int duration = 0;
    public int gravity = Gravity.BOTTOM;
    public int x = 0;
    public int y = 0;
}
