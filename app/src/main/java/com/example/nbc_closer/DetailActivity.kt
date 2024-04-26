package com.example.nbc_closer

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.nbc_closer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    lateinit var detailData : UserData

    //현재 데이터를 보내고 있어서 해당 데이터는 주석처리 하였습니다.
    //[To-do] MainActivity에서 받은 '클릭된 UserData' 정보를 받아와야 한다. 임시로 값을 clickedUserData에 하드코딩해 주었다.
//    private val clickedUserData = UserData(R.drawable.user_img_yujin, "안유진", "bluecar@naver.com","010-9874-3216",false)
    //초기 뷰 설정 관련 메소드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContentView(binding.root)
        //앱 바 연결
        setSupportActionBar(binding.detailToolBar)
        //디테일 액티비티에 뒤로 가기 버튼 생성
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        /*[To-do] DetailButtonBarFragment의 버튼이 눌릴 때,
            1. Fragment에서 setOnClickListner 실행
            2. DetailActivity -> Fragment로 전화번호 (clickedUserData.number)를 전송
            3. 받아온 값을 암시적 intent로 외부에 보내 문자/통화
        */


    }

    //bundle로 수정
    private fun initView(){
        detailData = intent.getParcelableExtra<UserData>("detail")!!
        if(detailData.img == -1){
            binding.detailImg.setImageURI(detailData.uri)
        }
        else {
            binding.detailImg.setImageResource(detailData.img)
        }
        binding.detailName.text = detailData.name
        binding.detailEmail.text = detailData.email
        binding.detailNumber.text = detailData.number
        binding.detailStatusMessage.text = detailData.message
        binding.detailToolBar.title = ""

        //bundle 포장 시작
        var detailFragment = DetailButtonBarFragment()
        var numberBundle = Bundle()
        numberBundle.putString("number", detailData.number)
        Log.d("number", detailData.number)
        detailFragment.arguments = numberBundle

        //프래그먼트 매니저 사용
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.detail_btm_bar_frag, detailFragment)
        fragmentManager.commit()
        //프래그먼트 매니저 사용

        //bundle 포장 끝
    }
    // 뒤로 가기 기능 설정, intent 사용하지 않고 액티비티 finish() 처리하였습니다.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}