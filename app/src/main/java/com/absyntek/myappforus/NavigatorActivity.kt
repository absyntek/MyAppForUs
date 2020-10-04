package com.absyntek.myappforus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import com.absyntek.myappforus.base.BaseActivity
import com.absyntek.myappforus.utils.NavigatorDirectory

class NavigatorActivity : BaseActivity(){

    companion object {
        const val DESTINATION = "DESTINATION"
        @JvmStatic
        fun newIntent(context: Context, destination: NavigatorDirectory) =
            Intent(context, NavigatorActivity::class.java)
                .putExtra(DESTINATION, destination)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                finish()
            }
        }

        if (savedInstanceState == null) {
            val destination = intent.getSerializableExtra(DESTINATION) as? NavigatorDirectory

            if (destination == null) {
                //Timber.e(Error("No destination in intent: ${intent.toUri(0)}"))
                Toast.makeText(this, "not Good", Toast.LENGTH_LONG).show()
                finish()
                return
            }

            val fragment = destination.toFragment()
            if (fragment != null) {
                setContentView(R.layout.activity_navigator)
                supportFragmentManager.commit {
                    replace(R.id.container, fragment)
                    addToBackStack(fragment::class.qualifiedName)
                }
                return
            }

            /*val intent = destination.toIntent(this)
            if (intent != null) {
                startActivity(intent)
                finish()
            }*/
        }
    }
}