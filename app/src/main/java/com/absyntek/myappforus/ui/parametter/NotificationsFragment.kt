package com.absyntek.myappforus.ui.parametter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.absyntek.myappforus.NavigatorActivity
import com.absyntek.myappforus.utils.NavigatorDirectory
import com.absyntek.myappforus.OfflineActivity
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentNotificationsBinding
import com.absyntek.myappforus.models.User
import com.absyntek.myappforus.utils.ui.ensureVisible
import com.google.firebase.auth.FirebaseAuth


class NotificationsFragment : BaseFragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var auth: FirebaseAuth

    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentUser = appGlobals().currentUser!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        if (currentUser.isAdmin){
            binding.btnCreateUser.ensureVisible()
            binding.btnCreateUser.setOnClickListener {
                startActivity(NavigatorActivity.newIntent(requireContext(), NavigatorDirectory.CreateUser))
            }
        }

        binding.btnLogout.setOnClickListener {
            logOut()
        }

        return binding.root
    }

    private fun logOut(){
        FirebaseAuth.getInstance().signOut()
        requireActivity().finish()
        startActivity(Intent(requireContext(), OfflineActivity::class.java))
    }
}