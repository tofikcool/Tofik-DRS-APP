package com.tofik.task.ui.home.representation


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tofik.task.R
import com.tofik.task.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import android.util.TypedValue

import android.view.ViewGroup
import android.widget.*
import android.R.array
import android.util.Log


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    var inputValue=1

    var matArray:ArrayList<Int> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        init()
        return binding.root
    }

    private fun init() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setUpToolbar()



    }








    private fun setUpToolbar() {
        setHasOptionsMenu(true)
        binding.toolBar.title = "Home"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_log_out -> {
                viewModel.logOutUser()
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }

        }
        return true
    }
}
