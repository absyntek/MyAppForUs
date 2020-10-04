package com.absyntek.myappforus.ui.create_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentCreateUserBinding
import com.absyntek.myappforus.models.User
import com.absyntek.myappforus.utils.firebase.UserHelper
import com.absyntek.myappforus.utils.ui.PimpMyToast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*


class CreateUserFragment : BaseFragment(){

    private lateinit var binding: FragmentCreateUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var authCreate: FirebaseAuth
    private lateinit var pt: PimpMyToast

    private val fo = FirebaseOptions.Builder()
        .setDatabaseUrl("gs://myappforus.appspot.com")
        .setApiKey("AIzaSyAvRNLX8TfrOpAaeLwGD9wAtH-C6DjDcas")
        .setApplicationId("myappforus")
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pt = PimpMyToast(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        assignVar()
        assignVar()
        binding.btnCreate.setOnClickListener {
            createUser()
        }
        return binding.root
    }

    private fun assignVar(){
        try {
            val myApp = FirebaseApp.initializeApp(requireContext(), fo, "MyAppForUs")
            auth = FirebaseAuth.getInstance(myApp)
        }catch (e: IllegalStateException){
            authCreate = FirebaseAuth.getInstance(FirebaseApp.getInstance("MyAppForUs"))
        }
    }

    private fun createUser(){
        authCreate.createUserWithEmailAndPassword(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        ).addOnCompleteListener {result ->
            if (result.isSuccessful){
                authCreate.currentUser?.let {fbUser ->
                    createUserInFirestore(fbUser)
                }
            }else{
                pt.redTopToast("erreur : ${result.exception?.message}")
            }
        }
    }

    private fun createUserInFirestore(user: FirebaseUser) {
        UserHelper.getUser(user.uid)
            .addOnSuccessListener {
                if (it.get("uid") == null || it.get("uid") == user.uid) {
                    val urlPicture = if (user.photoUrl != null) user.photoUrl.toString() else ""
                    val username = binding.edtUserName.text.toString()
                    val uid: String = user.uid
                    val isAdmin = binding.cbIsAdmin.isChecked
                    val userToCreate = User(uid, username, urlPicture, isAdmin, Date())
                    UserHelper.createUser(userToCreate).addOnFailureListener { e ->
                        e.message?.let { mess ->
                            pt.redTopToast(mess)
                        }
                    }.addOnSuccessListener {
                        pt.greenTopToast("Success !!")
                        pt.timerLong {
                            authCreate.signOut()
                            requireActivity().finish()
                        }
                    }
                }
            }
    }
}