package io.github.kolacbb.toastutils

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.github.kolacbb.library.ToastUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        ToastUtils.init(this)
    }
}