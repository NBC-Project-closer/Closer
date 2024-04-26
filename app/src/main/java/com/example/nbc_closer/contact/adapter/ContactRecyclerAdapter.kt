package com.example.nbc_closer.contact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_closer.R
import com.example.nbc_closer.data.UserData

interface OnItemClick {
    fun onDataClick(data : UserData)
}

class ContactRecyclerAdapter(val data: MutableList<UserData>, val onItemClick: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView
        val name: TextView
        val like: ImageView
        val container: ConstraintLayout

        init {
            img = view.findViewById(R.id.contact_left_img)
            name = view.findViewById(R.id.contact_left_name)
            like = view.findViewById(R.id.contact_left_like)
            container = view.findViewById(R.id.contact_left_constraint)
        }
    }

    class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView
        val name: TextView
        val like: ImageView
        val container: ConstraintLayout

        init {
            img = view.findViewById(R.id.contact_right_img)
            name = view.findViewById(R.id.contact_right_name)
            like = view.findViewById(R.id.contact_right_like)
            container = view.findViewById(R.id.contact_right_constraint)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    //좌우 멀티뷰 타입 판단에 이용할 메소드, data의 인덱스가 짝수면 왼쪽, 홀수면 오른쪽
    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_contact_left, parent, false)
                LeftViewHolder(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_contact_right, parent, false)
                RightViewHolder(view)
            }
        }
    }

    //데이터 인덱스에 따라 다른 뷰의 홀더를 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder as LeftViewHolder
            if(data[position].img == -1){
                holder.img.setImageURI(data[position].uri)
            }
            else {
                holder.img.setImageResource(data[position].img)
            }
            holder.name.text = data[position].name
            setUserLike(data[position], holder.like)
            holder.like.setOnClickListener {
                addUserLike(data[position], holder.like)
            }
            holder.container.setOnClickListener {
                onItemClick.onDataClick(data[position])
            }
        } else if (position % 2 == 1) {
            holder as RightViewHolder
            if(data[position].img == -1){
                holder.img.setImageURI(data[position].uri)
            }
            else {
                holder.img.setImageResource(data[position].img)
            }
            holder.name.text = data[position].name
            holder.like.setOnClickListener {
                addUserLike(data[position], holder.like)
            }
            setUserLike(data[position], holder.like)
            holder.container.setOnClickListener {
                onItemClick.onDataClick(data[position])
            }
        }
    }

    private fun addUserLike(data: UserData, img: ImageView) {
        if (!data.like) {
            img.setImageResource(R.drawable.icon_fillstar)
            data.like = true
        } else {
            img.setImageResource(R.drawable.icon_star)
            data.like = false
        }
    }
    private fun setUserLike(data: UserData, img: ImageView){
        if(!data.like)
            img.setImageResource(R.drawable.icon_star)
        else
            img.setImageResource(R.drawable.icon_fillstar)
    }
}