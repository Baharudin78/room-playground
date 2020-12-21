package com.baharudin.latianroom.ui.insert

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.baharudin.latianroom.R
import com.baharudin.latianroom.data.User
import com.baharudin.latianroom.data.UserViewModel
import com.baharudin.latianroom.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding : FragmentAddBinding? = null
    private val binding  get() = _binding!!
    private lateinit var mUserViewHolder : UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)

      mUserViewHolder = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.button.setOnClickListener {
            saveData()
        }


    }
    private fun saveData(){
        val namaDepan = binding.etNama.text.toString()
        val namaBelakang = binding.etBelakang.text.toString()
        val Umur = binding.etUmur.text

        if (inputCheck(namaDepan,namaBelakang,Umur)){
            val user = User(0,namaDepan,namaBelakang,Integer.parseInt(Umur.toString()))
            mUserViewHolder.addUser(user)
            Toast.makeText(requireContext(), "data berhasil disimpan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "gagal menyimapan ", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(namaDepan : String, namaBelakang : String,Umur : Editable): Boolean{
        return !(TextUtils.isEmpty(namaDepan) && TextUtils.isEmpty(namaBelakang) && Umur.isEmpty())
    }
}