package io.github.kolacbb.toaster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.github.kolacbb.library.OriginToast
import io.github.kolacbb.library.OverLayToast
import io.github.kolacbb.library.SnackToast
import io.github.kolacbb.library.Toaster

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onViewClicked(v: View) {
        when (v.id) {
            R.id.new_act -> {
                startActivity(Intent(this, MainActivity::class.java))
            }

            /*middle*/
            R.id.toast_origin -> {
//                Toaster.show("short")
                ToastUtils.showBanner("Banner.")
            }
            R.id.toast_short -> {
                Toaster.show("Short Toast")
            }
            R.id.toast_long -> {
                Toaster.show("This is a long toast, that is why it will be show long time.")
            }
            R.id.toast_thread -> {
                Thread(Runnable { run { Toaster.showMiddle("This is a long thread toast, that is why it will be show long time.") } }).start()
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
//                Toaster.showMiddle("Short Toast")
            }
            R.id.toast_long_middle -> {
//                Toaster.showMiddle("This is a long toast, that is why it will be show long time.")
            }
            R.id.toast_thread_middle -> {
//                Thread(Runnable { run { Toaster.showMiddle("Thread.") } }).start()
            }

            /* origin toast */
            R.id.toast_ori_custom -> {
                val toast = OriginToast(application)
                val view = TextView(this)
                view.text = "Short Custom Toast"
                toast.view = view
                toast.show()
            }
            R.id.toast_short_ori -> {
                val toast = OriginToast(application)
                toast.text = "Short Toast"
                toast.show()
            }
            R.id.toast_long_ori -> {
                val toast = OriginToast(application)
                toast.text = "This is a long toast, that is why it will be show long time."
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.duration = Toast.LENGTH_LONG
                toast.show()
            }
            R.id.toast_thread_ori -> {
                Thread(Runnable {
                    run {
                        val toast = OriginToast(application)
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
