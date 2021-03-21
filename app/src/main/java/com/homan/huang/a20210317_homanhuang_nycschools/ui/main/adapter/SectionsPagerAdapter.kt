package com.homan.huang.a20210317_homanhuang_nycschools.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.background.helper.lgi
import com.homan.huang.a20210317_homanhuang_nycschools.ui.main.SchoolInfoFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fa: FragmentActivity, val size: Int):
    FragmentStateAdapter(fa) {

    // total tabs
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        lgi("Create new fragement: ${position+1}")
        return SchoolInfoFragment.newInstance(position + 1)
    }
}