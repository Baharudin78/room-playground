package com.baharudin.latianroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.latianroom.data.User
import com.baharudin.latianroom.databinding.ItemListBinding

class ListAdapter(private var listener : OnclickUser) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private var userList = emptyList<User>()

    inner class ListHolder(binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){

        private var tvId : TextView = binding.tvId
        private var tvNamaDepan : TextView = binding.tvNamadepan
        private var tvNamaBelakang : TextView = binding.tvNamabelakang
        private var tvUmur: TextView = binding.tvUmur

        init {
            binding.rowItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    listener.OnclickUser(item)
                }

            }
        }

        private fun getItem(position: Int): User {
            return userList.get(position)
        }

        fun bind(user: User){
            tvId.text = user.id.toString()
            tvNamaDepan.text = user.namaDepan
            tvNamaBelakang.text = user.namaBelakang
            tvUmur.text = user.umur.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser)

    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
    interface OnclickUser{
        fun OnclickUser(user: User)
    }
}
