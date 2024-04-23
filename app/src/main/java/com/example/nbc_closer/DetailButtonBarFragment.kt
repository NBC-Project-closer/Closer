package com.example.nbc_closer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbc_closer.databinding.FragmentDetailButtonBarBinding

class DetailButtonBarFragment: Fragment() {
    private val binding by lazy {FragmentDetailButtonBarBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    //http://developer.android.com/intl/ko/reference/android/content/Intent.html 문자, 또는 통화를 할 때 필요한 암시적 인텐트
//        val messageIntent = Intent( Intent.ACTION_SENDTO, Uri.parse("tel:"+binding.detailNumber.text) )
//        val callIntent = Intent( Intent.ACTION_DIAL, Uri.parse("sms:"+binding.detailNumber.text) )

}