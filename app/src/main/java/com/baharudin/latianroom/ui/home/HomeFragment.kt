package com.baharudin.latianroom.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.latianroom.R
import com.baharudin.latianroom.adapter.ListAdapter
import com.baharudin.latianroom.data.User
import com.baharudin.latianroom.data.UserViewModel
import com.baharudin.latianroom.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home),ListAdapter.OnclickUser {
    private lateinit var mVIewModel : UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val adapter = ListAdapter(this)
        val recyclerView = binding.rvList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mVIewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mVIewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
    }

    override fun OnclickUser(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(user)
        findNavController().navigate(action)
    }

}