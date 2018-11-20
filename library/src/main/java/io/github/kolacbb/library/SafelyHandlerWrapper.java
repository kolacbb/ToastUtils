package io.github.kolacbb.library;

import android.os.Handler;
import android.os.Message;

public class SafelyHandlerWrapper extends Handler {
    private Handler impl;

    public SafelyHandlerWrapper(Handler impl) {
        this.impl = impl;
    }

    @Override
    public void dispatchMessage(Message msg) {
        try {
            super.dispatchMessage(msg);
        } catch (Exception e) {}
    }

    @Override
    public void handleMessage(Message msg) {
        impl.handleMessage(msg);
    }
}
