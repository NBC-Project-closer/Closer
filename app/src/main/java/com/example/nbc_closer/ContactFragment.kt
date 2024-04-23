package com.example.nbc_closer

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
        setHasOptionsMenu(true)
    }

    //R.menu.app_bar_menu에 해당하는 리소스를 해당 contact 프래그먼트 기능에 만들기
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //R.menu.app_bar_menu_에 해당하는 리소스를 클릭시 작동할 기능 관련 메소드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.show_grid -> {
                    Log.d("D", "그리드 형식 클릭")
                    // 기능 채워 넣기
                   }
                else -> {Log.d("D", "리스트 형식 클릭")
                    //기능 채워 넣기
                        }
        }
        return super.onOptionsItemSelected(item)
    }

}