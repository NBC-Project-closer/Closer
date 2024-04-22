package com.example.nbc_closer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbc_closer.databinding.FragmentContactDetailBinding

class ContactDetailFragment: Fragment() {
    private val binding by lazy { FragmentContactDetailBinding.inflate(layoutInflater) }


    //프래그먼트에 대해 레이아웃을 제공하려면 반드시 onCreateView()콜백 메서드를 구현
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}