package com.wipro.news.app.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wipro.news.app.view.fragments.HistoryFragment
import com.wipro.news.app.view.fragments.NewsListFragment

class TabsAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewsListFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> NewsListFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}