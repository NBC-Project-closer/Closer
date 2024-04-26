package com.example.nbc_closer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nbc_closer.databinding.FragmentDetailButtonBarBinding

class DetailButtonBarFragment: Fragment() {

    //DetailActivity에서 여기로 번호를 가져와야 함.
    //Activity에서 번들 사용해서 가져오면 됨! <<< index 찝기

    //번들 받기 끝
    private val binding by lazy {FragmentDetailButtonBarBinding.inflate(layoutInflater)}

    override fun onCreateView( //
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //detail 페이지에서 외부 문자, 전화 앱으로 이동
        //번들 받기 시작
        val detailPhoneNumber = arguments?.getString("number")
        Log.d("number2",
            detailPhoneNumber.toString()) //.toString()
        binding.detailMessageBtn.setOnClickListener {
            val messageIntent = Intent( Intent.ACTION_SENDTO, Uri.parse("sms:" + detailPhoneNumber) )
            startActivity(messageIntent)
            //Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
        }
        binding.detailCallBtn.setOnClickListener {
            val callIntent = Intent( Intent.ACTION_DIAL, Uri.parse("tel:" + detailPhoneNumber) )
            startActivity(callIntent)
            //Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show()
        }//암시적 인텐트 http://developer.android.com/intl/ko/reference/android/content/Intent.html
    }

}