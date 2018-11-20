package io.github.kolacbb.library;

import android.content.Context;
import android.widget.Toast;

public class OriginToast extends Toast implements IToast {

    public OriginToast(Context context) {
        super(context);
        ToastHandlerHooker.hook(this);
    }

    @Override
    public void apply(ToastParam param) {
        setView(param.view);
        setDuration(param.duration);
        setGravity(param.gravity, param.x, param.y);
    }
}
