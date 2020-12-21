package com.baharudin.latianroom.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.baharudin.latianroom.R
import com.baharudin.latianroom.adapter.ListAdapter
import com.baharudin.latianroom.data.User
import com.baharudin.latianroom.data.UserViewModel
import com.baharudin.latianroom.databinding.FragmentUpdateBinding
import com.baharudin.latianroom.ui.home.HomeFragmentDirections

class UpdateFragment : Fragment(R.layout.fragment_update),ListAdapter.OnclickUser {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel : UserViewModel
    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateBinding.bind(view)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.apply {
            binding.etUpdateNama.setText(args.userUpdate.namaDepan)
            binding.etUpdateBelakang.setText(args.userUpdate.namaBelakang)
            binding.etUpdateUmur.setText(args.userUpdate.umur.toString())

            btUpdate.setOnClickListener {
                updateData()
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mViewModel.deleteUser(args.userUpdate)
            Toast.makeText(requireContext(), "Berhasil menghapus : ${args.userUpdate.namaDepan}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete ${args.userUpdate.namaDepan}?")
        builder.setMessage("Apakah yakin mau hapus ${args.userUpdate.namaDepan}")
        builder.create().show()
    }

    private fun updateData(){
        val namaDepan = binding.etUpdateNama.text.toString()
        val namaBelakang = binding.etUpdateBelakang.text.toString()
        val umur = Integer.parseInt(binding.etUpdateUmur.text.toString())

        if (inputCheck(namaDepan,namaBelakang,binding.etUpdateUmur.text)){
            val updateUser = User(args.userUpdate.id,namaDepan,namaBelakang,umur)
            mViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Sukses update", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "tolong di isi", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(namaDepan : String, namaBelakang : String,Umur : Editable): Boolean{
        return !(TextUtils.isEmpty(namaDepan) && TextUtils.isEmpty(namaBelakang) && Umur.isEmpty())
    }
    override fun OnclickUser(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(user)
        findNavController().navigate(action)
    }
}