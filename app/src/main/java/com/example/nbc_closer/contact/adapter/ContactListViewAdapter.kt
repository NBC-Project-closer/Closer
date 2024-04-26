package com.example.nbc_closer.contact.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nbc_closer.contact.ContactFragment
import com.example.nbc_closer.mypage.MyPageFragment

class ContactListViewAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragmentList = listOf<Fragment>(
        ContactFragment(),
        MyPageFragment()
    )
    override fun getItemCount(): Int {
      return  fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
