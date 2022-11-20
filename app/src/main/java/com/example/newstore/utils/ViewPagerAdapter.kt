package com.example.newstore.utils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*


class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(
    manager!!
) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()


    override fun getItem(position: Int): Fragment {

        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    fun getFragment(pos: Int): Fragment {
        return mFragmentList[pos]
    }

}