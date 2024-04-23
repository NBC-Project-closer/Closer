package com.example.nbc_closer

import android.content.Intent
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
        initFragment()
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
                   initGrid()
                   }
                else -> {Log.d("D", "리스트 형식 클릭")
                    initFragment()
                        }
        }
        return super.onOptionsItemSelected(item)
    }
    //초기 설정을 함수로 내렸습니다, onViewCreated를 깔끔하게 하기 위해
    private fun initFragment() {
        binding.contactRecyclerView.adapter = ContactRecyclerAdapter(datalist, object : OnItemClick{
            override fun onDataClick(data: UserData) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("detail", data)
                startActivity(intent)
            }
        })
        binding.contactRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.contactBtnAdd.setOnClickListener {
            binding.contactBtnAdd.setBackgroundResource(R.color.btn_color)
            binding.contactTvBtnAdd.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
    private fun initGrid(){
        binding.contactRecyclerView.adapter = ContactGridAdapter(datalist,object :OnItemClick{
            override fun onDataClick(data: UserData) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("detail", data)
                startActivity(intent)
            }
        })
        binding.contactRecyclerView.layoutManager = GridLayoutManager(this.context,2)
        binding.contactBtnAdd.setOnClickListener {
            binding.contactBtnAdd.setBackgroundResource(R.color.btn_color)
            binding.contactTvBtnAdd.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

}