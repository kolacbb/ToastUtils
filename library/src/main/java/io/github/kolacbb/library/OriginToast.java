package io.github.kolacbb.library;

import android.content.Context;
import android.widget.Toast;

public class OriginToast extends Toast implements IToast {

    private ToastParam mToastParam;

    public OriginToast(Context context) {
        super(context);
    }

    @Override
    public void apply(ToastParam param) {
        mToastParam = param;
        setView(param.view);
        setDuration(param.duration);
        setGravity(param.gravity, param.x, param.y);
        ToastHandlerHooker.hook(this);
    }

    @Override
    public void show() {
        if (mToastParam.view == null) {
            mToastParam.view = ToastUtils.createView(mToastParam.text);
        }
        super.show();
    }
}
