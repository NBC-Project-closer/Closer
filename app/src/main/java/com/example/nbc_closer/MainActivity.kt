package com.example.nbc_closer


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc_closer.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContentView(binding.root)
        //앱 바 액티비티에 묶기
        setSupportActionBar(binding.mainToolBar)

    }


    /* [수영 요청사항] 클릭받은 item의 UserData값을 DetailActivity로 보내주시면 됩니다.

    궁금한 점: datalist에 접근하려면 현재 어떻게 해야 하나요?
    질문하는 이유: DetailActivity에서도 datalist에 접근 가능해지면 datalist의 index를 가지고 불러올 수 있음
    질문하는 이유 2: 좋아요/즐겨찾기 입력이 UserData에 저장되는 게 아니라 MainActivity에 저장되는 것이라면
    index가 아닌 선택된 UserData값을 그대로 보내주셔야 할 것 같아서요.
    */

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

