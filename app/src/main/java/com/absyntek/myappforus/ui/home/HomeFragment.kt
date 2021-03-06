package com.absyntek.myappforus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.absyntek.myappforus.NavigatorActivity
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentHomeBinding
import com.absyntek.myappforus.models.Product
import com.absyntek.myappforus.utils.NavigatorDirectory
import com.absyntek.myappforus.utils.firebase.ProductHelper
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bind: FragmentHomeBinding
    private lateinit var adapterActual: HomeAdapter
    private lateinit var adapterNext: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val optionActual = FirestoreRecyclerOptions.Builder<Product>().setQuery(ProductHelper.queryActual(), Product::class.java).build()
        adapterActual = HomeAdapter(optionActual, ::productClick)
        val optionNext = FirestoreRecyclerOptions.Builder<Product>().setQuery(ProductHelper.queryNext(), Product::class.java).build()
        adapterNext = HomeAdapter(optionNext, ::productClick)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        bind = FragmentHomeBinding.inflate(layoutInflater, container, false)

        bind.tvName.text = "Welcome ${appGlobals().currentUser?.userName}"

        bind.rvHomeActual.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        bind.rvHomeActual.adapter = adapterActual

        bind.rvHomeNext.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        bind.rvHomeNext.adapter = adapterNext

        return bind.root
    }

    private fun productClick(product: Product){
        startActivity(NavigatorActivity.newIntent(requireContext(), NavigatorDirectory.ProductFr(product)))
    }

    override fun onResume() {
        super.onResume()
        adapterActual.startListening()
        adapterNext.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapterActual.stopListening()
        adapterNext.startListening()
    }
}