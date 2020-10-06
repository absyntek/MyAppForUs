package com.absyntek.myappforus

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseActivity
import com.absyntek.myappforus.databinding.ActivityMainAdminBinding
import com.absyntek.myappforus.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var bind: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = if (appGlobals().admin){
            ActivityMainAdminBinding.inflate(layoutInflater)
        }else{
            ActivityMainBinding.inflate(layoutInflater)
        }

        setContentView(bind.root)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}