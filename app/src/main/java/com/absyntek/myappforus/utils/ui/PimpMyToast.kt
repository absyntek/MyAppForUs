package com.absyntek.myappforus.utils.ui

import android.animation.TimeInterpolator
import android.content.Context
import android.graphics.Interpolator
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import com.absyntek.myappforus.R
import qw.greendroid.library.Toastest
import timber.log.Timber
import java.lang.IllegalArgumentException

/**
 * Source :  https://android-arsenal.com/details/1/7509
 *
 *     --- Usage of Toastest ---
 *
 *     Each method always returns a Toast object, so you can customize the Toast with below methods.
 *
 *  -** This displays a Toast with an icon. :
 *.          Toastest.makeToast(context, "This is a toast with an icon.", iconDrawable,Toast.LENGTH_SHORT).show()
 *
 *  -** This displays a Toast with a new background.
 *          Toastest.makeToast(context, background, "This a toast with a background.", Toast.LENGTH_SHORT).show()
 *
 *  -** This displays a Toast with a new font type.
 *          Toastest.makeToast(context, "This a toast with a new font type.", TEXT_TYPEFACE, Toast.LENGTH_SHORT).show()
 *
 *  -** This displays a Toast with a new text size.
 *          Toastest.makeToast(context, "This a toast with a new text size.", TEXT_SIZE, Toast.LENGTH_SHORT).show()
 *
 *  -** The makeGravity() method makes a Toast with new Gravity:
 *          makeGravity(toast,YOUR_GRAVITY)
 *
 *  -**  The makeAnim() method makes a toast by ObjectAnimator as below:
 *          makeAnim(toast, propertyName, animDuration, animInterpolator, from, to)
 *
 *  -**  And you can create your custom Toasts with the custom() method:
 *          Toastest.custom(context, "This is a fully customized toast.", iconDrawable, backgroundDrawable, TEXT_SIZE, TEXT_TYPEFACE, Toast.LENGTH_SHORT).show()
 */


class PimpMyToast (private val context: Context?){

    fun greenTopToast(text:String) =
        context?.let {
            try {
                val toast =
                    Toastest.custom(
                        it,
                        text,
                        null,
                        it.getDrawable(R.drawable.background_toast_green_light),
                        Typeface.DEFAULT,
                        getColor(it,R.color.green_dark),
                        16 ,
                        Toast.LENGTH_LONG
                    )
                Toastest.makeGravity(toast, Gravity.TOP)
                toast.show()
            }catch (e:IllegalArgumentException){
                Timber.e(e)
            }

        }

    fun orangeTopToast(text:String) =
        context?.let {
            try {
                val toast =
                    Toastest.custom(
                        it,
                        text,
                        null,
                        it.getDrawable(R.drawable.background_toast_orange_light),
                        Typeface.DEFAULT,
                        getColor(it,R.color.orange_dark),
                        16 ,
                        Toast.LENGTH_LONG
                    )
                Toastest.makeGravity(toast, Gravity.TOP)
                toast.show()
            }catch (e:IllegalArgumentException){
                Timber.e(e)
            }

        }

    fun redTopToast(text:String) =
        context?.let {
            try {
                val toast =
                    Toastest.custom(
                        it,
                        text,
                        null,
                        it.getDrawable(R.drawable.background_toast_red_light),
                        Typeface.DEFAULT,
                        getColor(it,R.color.red_dark),
                        16 ,
                        Toast.LENGTH_LONG
                    )
                Toastest.makeGravity(toast, Gravity.TOP)
                toast.show()
            }catch (e:IllegalArgumentException){
                Timber.e(e)
            }

        }

    fun timerLong(foo:() -> Unit){
        val timer = object: CountDownTimer(3500, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                foo()
            }
        }
        timer.start()
    }

    fun timerShort(foo:() -> Unit){
        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                foo()
            }
        }
        timer.start()
    }
}