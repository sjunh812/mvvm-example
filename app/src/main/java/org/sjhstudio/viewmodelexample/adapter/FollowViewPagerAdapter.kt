package org.sjhstudio.viewmodelexample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    var fragmentItems: ArrayList<Fragment> = arrayListOf()

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]
    }

    override fun getItemCount(): Int {
        return fragmentItems.size
    }
}