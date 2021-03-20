package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.background.helper.lgd
import com.homan.huang.a20210317_homanhuang_nycschools.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fa: FragmentActivity, val size: Int):
    FragmentStateAdapter(fa) {

    // total tabs
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment =
        PlaceholderFragment.newInstance(position + 1)
}