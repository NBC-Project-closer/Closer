package com.example.nbc_closer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbc_closer.databinding.FragmentContactDetailBinding


class ContactDetailFragment: Fragment() {
    private val binding by lazy { FragmentContactDetailBinding.inflate(layoutInflater) }

    //0. MainActivity.kt에서 현재 눌려진 값이 UserData의 몇 번재 항인지 받아오기.
    val clickedUserData = UserData(R.drawable.user_img_yujin, "안유진", "bluecar@naver.com","010-9874-3216",false)
    //[to-do] clickedUserData에 눌려진 버튼의 UserData 값을 보내주시면 됩니다!
    //프래그먼트에 대해 레이아웃을 제공하려면 반드시 onCreateView()콜백 메서드를 구현
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.detailImg.setImageResource(clickedUserData.img)
        binding.detailName.text = clickedUserData.name
        binding.detailEmail.text = clickedUserData.email
        binding.detailNumber.text = clickedUserData.number

        //http://developer.android.com/intl/ko/reference/android/content/Intent.html 문자, 또는 통화를 할 때 필요한 암시적 인텐트
//        val messageIntent = Intent( Intent.ACTION_SENDTO, Uri.parse("tel:"+binding.detailNumber.text) )
//        val callIntent = Intent( Intent.ACTION_DIAL, Uri.parse("sms:"+binding.detailNumber.text) )
        return binding.root
    }


}