package com.example.nbc_closer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_closer.databinding.ItemContactGridBinding

class ContactGridAdapter(val data:MutableList<UserData>,val onItemClick: OnItemClick):RecyclerView.Adapter<ContactGridAdapter.GridAdapter>() {
    class GridAdapter(private val binding:ItemContactGridBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(userData:UserData){
            binding.gridUserImg.setImageResource(userData.img)
            binding.gridUserName.text = userData.name
            binding.gridLike.setOnClickListener{
                if(!userData.like){
                    binding.gridLike.setImageResource(R.drawable.icon_fillstar)
                    userData.like = true
                }
                else{
                    binding.gridLike.setImageResource(R.drawable.icon_star)
                    userData.like = false
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter {
        val binding = ItemContactGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GridAdapter(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GridAdapter, position: Int) {
        holder.bind(data[position])
    }
}