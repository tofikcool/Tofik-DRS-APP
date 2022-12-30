package com.tofik.task.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tofik.task.R
import com.tofik.task.util.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var sessionManagement: SessionManagement
    private val handler = Handler()
    private val runnable = Runnable {
        if (sessionManagement.isLoggedIn()) {
            findNavController().navigate(
                R.id.homeFragment
            )
        } else {
            findNavController().navigate(
                R.id.loginFragment
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        handler.postDelayed(runnable, 500)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onDestroyView() {
        handler.removeCallbacks(runnable)
        super.onDestroyView()
    }
}