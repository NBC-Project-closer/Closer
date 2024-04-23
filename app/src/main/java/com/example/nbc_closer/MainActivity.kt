package com.example.nbc_closer

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
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
    }
}