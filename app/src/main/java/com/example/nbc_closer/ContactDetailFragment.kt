package com.example.nbc_closer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ContactDetailFragment: Fragment() {
    //프래그먼트에 대해 레이아웃을 제공하려면 반드시 onCreateView()콜백 메서드를 구현
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_detail, container,false)
    }
}