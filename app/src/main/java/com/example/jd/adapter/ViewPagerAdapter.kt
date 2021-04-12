package com.example.jd.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 *    author : Tiaw.
 *    date   : 4/8/21
 *    博客：https://blog.csdn.net/weixin_44819566
 *    desc   :
 */
class ViewPagerAdapter( fm: FragmentManager, val list: List<Fragment>) :
    FragmentPagerAdapter(fm) {

    val titlelist = listOf("首页","联系人","朋友圈","设置")

    override fun getPageTitle(position: Int): CharSequence? {
        return titlelist[position]
    }
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

}
