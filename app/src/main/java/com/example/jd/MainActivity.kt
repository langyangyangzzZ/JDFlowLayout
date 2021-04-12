package com.example.jd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.Message
import androidx.core.widget.NestedScrollView
import androidx.viewpager.widget.ViewPager
import com.example.jd.adapter.ViewPagerAdapter
import com.example.jd.fragments.BlankFragment
import com.example.jd.fragments.ViewGroup2Fragment
import com.example.jd.fragments.ViewGroupFragment
import com.google.android.material.tabs.TabLayout
import java.util.logging.Handler
import android.os.MessageQueue as MessageQueue1


class MainActivity : AppCompatActivity() {

    val tablayout by lazy {
        findViewById<TabLayout>(R.id.table)
    }

    val viewpager by lazy {
        findViewById<ViewPager>(R.id.viewpager)
    }
    val nestScrool by lazy {
        findViewById<NestedScrollView>(R.id.nestScrool)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val blankFragment1 = ViewGroupFragment()
        val blankFragment2 = ViewGroup2Fragment()
        val blankFragment3 = BlankFragment()
        val blankFragment4 = ViewGroupFragment()

        val list = listOf(blankFragment1, blankFragment2, blankFragment3, blankFragment4)

        viewpager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            list
        )

        tablayout.setupWithViewPager(viewpager)

        viewpager.post {
            viewpager.layoutParams.height == nestScrool.height
            viewpager.requestLayout()
        }

    }
}