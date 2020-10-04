package com.absyntek.myappforus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.absyntek.myappforus.api.ServiceLocator
import com.absyntek.myappforus.api.UseFullRes
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseActivity
import com.absyntek.myappforus.databinding.ActivityOfflineBinding
import com.absyntek.myappforus.models.User
import com.absyntek.myappforus.utils.firebase.UserHelper
import com.absyntek.myappforus.utils.ui.PimpMyToast
import com.absyntek.myappforus.utils.ui.ensureGone
import com.absyntek.myappforus.utils.ui.ensureVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OfflineActivity : BaseActivity(){

    private lateinit var bind: ActivityOfflineBinding
    private lateinit var auth: FirebaseAuth

    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityOfflineBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ServiceLocator.initialize(UseFullRes())
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        currentUser = auth.currentUser
        if (currentUser != null){
            PimpMyToast(this).timerLong {
                launchMainActivity()
            }
        }else{
            bind.pgbOff.ensureGone()
            bind.layoutConnect.ensureVisible()
            bind.btnConnect.setOnClickListener {
                tryConnect(bind.edtEmail.text.toString(), bind.edtPassword.text.toString())
            }
        }
    }

    private fun tryConnect(email:String, pass:String){
        auth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                launchMainActivity()
            }
    }

    private fun launchMainActivity(){
        val usr = auth.currentUser
        if (usr != null){
            UserHelper.getUser(usr.uid).addOnSuccessListener {doc ->
                val user = User.createFromDocuument(doc)
                user?.let {userT ->
                    appGlobals().currentUser = userT
                    appGlobals().firebaseUser = usr
                    this.finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }

        }else {
            Toast.makeText(this, "non authorized", Toast.LENGTH_LONG).show()
        }
    }
}