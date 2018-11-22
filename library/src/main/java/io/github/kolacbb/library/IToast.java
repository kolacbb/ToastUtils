package io.github.kolacbb.library;

public interface IToast {

    int getDuration();

    void show();

    void cancel();

    void apply(ToastParam param);

}
