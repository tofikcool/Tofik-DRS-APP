package com.tofik.task.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tofik.task.R
import com.tofik.task.databinding.FragmentLoginBinding
import com.tofik.task.util.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {


    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sessionManagement: SessionManagement

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (sessionManagement.isLoggedIn()) {
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            )
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginButton.setOnClickListener(this)
        subscribeToObservers()

        return binding.root
    }

    private fun subscribeToObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                viewModel.doneShowingError()
            }
        })
        viewModel.navigateToMap.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    )
                    viewModel.doneNavigating()
                }
            }
        })
        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun loginUser(email: String, password: String) {
        if (viewModel.validateEmail(email) == "ok") {

            if (viewModel.validatePassword(password) == "ok") {
                viewModel.loginUser(email, password)
            } else {
                binding.passwordEditText.error = viewModel.validatePassword(password)
            }
        } else {
            binding.emailEditText.error = viewModel.validateEmail(email)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login_button ->
                loginUser(
                    binding.emailEditText.editText?.text.toString().trim()
                    , binding.passwordEditText.editText?.text.toString().trim()
                )
        }
    }
}
