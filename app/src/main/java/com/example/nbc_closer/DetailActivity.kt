package com.example.nbc_closer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc_closer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    //[To-do] MainActivity에서 받은 '클릭된 UserData' 정보를 받아와야 한다. 임시로 값을 clickedUserData에 하드코딩해 주었다.
    private val clickedUserData = UserData(R.drawable.user_img_yujin, "안유진", "bluecar@naver.com","010-9874-3216",false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.detailImg.setImageResource(clickedUserData.img)
        binding.detailName.text = clickedUserData.name
        binding.detailEmail.text = clickedUserData.email
        binding.detailNumber.text = clickedUserData.number
        setContentView(binding.root)

        /*[To-do] DetailButtonBarFragment의 버튼이 눌릴 때,
            1. Fragment에서 setOnClickListner 실행
            2. DetailActivity -> Fragment로 전화번호 (clickedUserData.number)를 전송
            3. 받아온 값을 암시적 intent로 외부에 보내 문자/통화
        */

    }
}