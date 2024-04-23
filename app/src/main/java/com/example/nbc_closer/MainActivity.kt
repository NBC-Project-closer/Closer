package com.example.nbc_closer

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nbc_closer.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        initView()
        setContentView(binding.root)

        //앱 바 액티비티에 묶기
        setSupportActionBar(binding.mainToolBar)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

    }

    // 초기 뷰 설정
    private fun initView(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.contractViewPager.adapter = ContactListViewAdapter(this)
        TabLayoutMediator(binding.contractTab, binding.contractViewPager){tab, position ->
            when(position){
                0 -> tab.text = "연락처"
                else -> tab.text = "마이페이지"
            }
        }.attach()
        binding.contractTab.setTabTextColors(Color.rgb(80,80,80), R.color.font_color)
        binding.mainToolBar.title = ""
        }

    //앱바 메뉴 설정, 프래그먼트에서 레이아웃 변경 기능이 있으므로 액티비티에서는 별도 기능 추가 X
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


}