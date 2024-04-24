package com.example.nbc_closer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nbc_closer.databinding.FragmentDetailButtonBarBinding

class DetailButtonBarFragment: Fragment() {
    private var detailPhoneNumber = "010-9874-3216" // 나중에 가지고 와야.
    private val binding by lazy {FragmentDetailButtonBarBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //detail 페이지에서 외부 문자, 전화 앱으로 이동
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

        return binding.root
    }

}