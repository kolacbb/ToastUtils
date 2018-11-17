package io.github.kolacbb.toastutils

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.widget.Button
import android.widget.Toast
import io.github.kolacbb.library.ToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastUtils.init(application)
        setContentView(R.layout.activity_main)




        findViewById<Button>(R.id.new_act).setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        val taost =  Toast.makeText(
                this,
        "This is a long toast, that is why it will be show long time.",
        Toast.LENGTH_SHORT)
        var a = 0
        findViewById<Button>(R.id.toast_origin).setOnClickListener {
            taost.setText("This is a long toast, that is why it will be show long time." + a++)
            taost.show()
        }
        findViewById<Button>(R.id.toast_long).setOnClickListener { ToastUtils.show("This is a long toast, that is why it will be show long time.") }
        findViewById<Button>(R.id.toast_short).setOnClickListener { ToastUtils.show("Short Toast") }
        findViewById<Button>(R.id.toast_thread).setOnClickListener {
            Thread(Runnable { run { ToastUtils.show("Thread.") } }).start()
        }
    }
}