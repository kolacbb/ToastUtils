package io.github.kolacbb.toastutils

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.github.kolacbb.library.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastUtils.init(application)
        setContentView(R.layout.activity_main)
    }

    fun onViewClicked(v: View) {
        when (v.id) {
            R.id.new_act -> {
                startActivity(Intent(this, MainActivity::class.java))
            }

            /*middle*/
            R.id.toast_origin -> {
                Toast.makeText(this, "Short Toast.", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.toast_short -> {
                ToastUtils.show("Short Toast")
            }
            R.id.toast_long -> {
                ToastUtils.show("This is a long toast, that is why it will be show long time.")
            }
            R.id.toast_thread -> {
                Thread(Runnable { run { ToastUtils.show("Thread.") } }).start()
            }

            /*middle*/
            R.id.toast_origin_middle -> {
                val toast = Toast.makeText(
                    this,
                    "This is a long toast, that is why it will be show long time.",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            R.id.toast_short_middle -> {
                ToastUtils.showMiddle("Short Toast")
            }
            R.id.toast_long_middle -> {
                ToastUtils.showMiddle("This is a long toast, that is why it will be show long time.")
            }
            R.id.toast_thread_middle -> {
                Thread(Runnable { run { ToastUtils.showMiddle("Thread.") } }).start()
            }

            /* origin toast */
            R.id.toast_ori_custom -> {
                val toast = OriginToast(baseContext)
                val view = TextView(this)
                view.text = "Short Custom Toast"
                toast.view = view
                toast.show()
            }
            R.id.toast_short_ori -> {
                val toast = OriginToast(baseContext)
                toast.text = "Short Toast"
                toast.show()
            }
            R.id.toast_long_ori -> {
                val toast = OriginToast(baseContext)
                toast.text = "This is a long toast, that is why it will be show long time."
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.duration = Toast.LENGTH_LONG
                toast.show()
            }
            R.id.toast_thread_ori -> {
                Thread(Runnable {
                    run {
                        val toast = OriginToast(baseContext)
                        toast.text = "Snack Thread"
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }).start()
            }

            /* snack toast */
            R.id.toast_snack_custom -> {
                val toast = SnackToast(baseContext)
                val view = TextView(this)
                view.text = "Short Custom Toast"
                toast.view = view
                toast.show()
            }
            R.id.toast_short_snack -> {
                val toast = SnackToast(baseContext)
                toast.text = "Short Toast"
                toast.show()
            }
            R.id.toast_long_snack -> {
                val toast = SnackToast(baseContext)
                toast.text = "This is a long toast, that is why it will be show long time."
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.duration = Toast.LENGTH_LONG
                toast.show()
            }
            R.id.toast_thread_snack -> {
                Thread(Runnable {
                    run {
                        val toast = SnackToast(baseContext)
                        toast.text = "Snack Thread"
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }).start()
            }

            /* overlay toast */
            R.id.toast_overlay_custom -> {
                val toast = OverLayToast(baseContext)
                val view = TextView(this)
                view.text = "Short Custom Toast"
                toast.view = view
                toast.show()
            }
            R.id.toast_short_overlay -> {
                val toast = OverLayToast(baseContext)
                toast.text = "Short Toast"
                toast.show()
            }
            R.id.toast_long_overlay -> {
                val toast = OverLayToast(baseContext)
                toast.text = "This is a long toast, that is why it will be show long time."
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.duration = Toast.LENGTH_LONG
                toast.show()
            }
            R.id.toast_thread_overlay -> {
                Thread(Runnable {
                    run {
                        val toast = OverLayToast(baseContext)
                        toast.text = "Snack Thread"
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }).start()
            }

        }
    }
}
