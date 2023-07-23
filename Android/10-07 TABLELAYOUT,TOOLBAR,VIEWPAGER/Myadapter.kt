package com.example.chatapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class Myadapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {
    var titlelist:MutableList<String> =ArrayList()
    var fragmentlist:MutableList<Fragment> =ArrayList()

    override fun getCount(): Int {
        return fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentlist.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlelist.get(position)
    }
    fun myfun(title:String,fragment: Fragment)
    {
        titlelist.add(title)
        fragmentlist.add(fragment)
    }
}