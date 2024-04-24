package com.example.nbc_closer

import android.annotation.SuppressLint
import android.app.Dialog
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
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_closer.databinding.FragmentContactBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var isFloatingButtonClick : Boolean = false
var addSavedButtonClicked : Boolean = false
class ContactFragment : Fragment() {
    lateinit var binding : FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(layoutInflater)

        //ItemTouchHelper 구현 중...
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false //드래그 사용 안함, 그치만 아예 빼면 오류남
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

            }
        })
        itemTouchHelper.attachToRecyclerView(binding.contactRecyclerView)
        //ItemTouchHelper 구현 중...

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFragment()
        addData()
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
        //플로팅 버튼 설정 관련 메소드
        binding.contactFloatingBtn.setOnClickListener {
            if(!isFloatingButtonClick){
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_x)
                binding.contactFloatingAdd.visibility = View.VISIBLE
                binding.contactFloatingAlarm.visibility = View.VISIBLE
                binding.contactFloatingLoad.visibility = View.VISIBLE
                isFloatingButtonClick = true
            }
            else {
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_plus)
                binding.contactFloatingAdd.visibility = View.GONE
                binding.contactFloatingAlarm.visibility = View.GONE
                binding.contactFloatingLoad.visibility = View.GONE
                isFloatingButtonClick = false
            }
        }
        binding.contactFloatingAdd.setOnClickListener {
            Log.d("확인", "디이얼로그오픈")
            openAddDialog()
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
        //플로팅 버튼 설정 관련 메소드
        binding.contactFloatingBtn.setOnClickListener {
            if(!isFloatingButtonClick){
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_x)
                binding.contactFloatingAdd.visibility = View.VISIBLE
                binding.contactFloatingAlarm.visibility = View.VISIBLE
                binding.contactFloatingLoad.visibility = View.VISIBLE
                isFloatingButtonClick = true
            }
            else {
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_plus)
                binding.contactFloatingAdd.visibility = View.GONE
                binding.contactFloatingAlarm.visibility = View.GONE
                binding.contactFloatingLoad.visibility = View.GONE
                isFloatingButtonClick = false
            }
        }

        binding.contactFloatingAdd.setOnClickListener {
            Log.d("확인", "디이얼로그오픈")
            openAddDialog()
        }

    }

    //다이얼로그 오픈
    private fun openAddDialog() {
        val dialog = SaveInfoDialogFragment()
        dialog.isCancelable = false
        dialog.show(requireFragmentManager(), "openDialog")
    }

    //코루틴을 활용하여 datalist 변화를 계속 감지하는 메소드
    @SuppressLint("NotifyDataSetChanged")
    private fun addData() {
        lifecycleScope.launch {
            while(true){
                delay(500)
                if(addSavedButtonClicked){
                    (binding.contactRecyclerView.adapter as ContactRecyclerAdapter).notifyDataSetChanged()
                    addSavedButtonClicked = false
                }
            }
        }
    }
}