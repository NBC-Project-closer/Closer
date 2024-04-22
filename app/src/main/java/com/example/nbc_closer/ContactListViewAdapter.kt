package com.example.nbc_closer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContactListViewAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragmentList = listOf<Fragment>(
        ContactFragment(),
        ContactDetailFragment()
    )
    override fun getItemCount(): Int {
      return  fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}