package com.example.nbc_closer

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc_closer.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    lateinit var binding : FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contactRecyclerView.adapter = ContactRecyclerAdapter(datalist, object : OnItemClick{
            override fun onDataClick(data: UserData) {
                val bundle = Bundle()
                bundle.putParcelable("detail", data)
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.main_activity, BlankFragment())
//                    .commit()
            }
        })
        binding.contactRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.contactBtnAdd.setOnClickListener {
            binding.contactBtnAdd.setBackgroundResource(R.color.btn_color)
            binding.contactTvBtnAdd.setTextColor(Color.parseColor("#FFFFFF"))
        }
        super.onViewCreated(view, savedInstanceState)
    }

}