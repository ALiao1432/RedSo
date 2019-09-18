package study.ian.redso.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import study.ian.redso.R
import study.ian.redso.service.ServiceBuilder
import study.ian.redso.util.ContentPagerAdapter

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: Toolbar
    private lateinit var contentPager: ViewPager
    private var teams = arrayOf("rangers", "elastic", "dynamo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ServiceBuilder.watchNetworkState(this)

        findViews()
        initViews()
    }

    private fun findViews() {
        tabLayout = findViewById(R.id.tabLayout)
        toolbar = findViewById(R.id.customToolbar)
        contentPager = findViewById(R.id.contentPager)
    }

    private fun initViews() {
        setupActionBar()
        setupTabLayout()
        setupViewPager()
    }

    private fun setupActionBar() {
        toolbar.title = ""

        val spannedRed = SpannableString("Red")
        spannedRed.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            spannedRed.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        val spannedSo = SpannableString("So")
        spannedSo.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            spannedSo.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        val toolbarText = toolbar.findViewById<TextView>(R.id.toolbarText)
        toolbarText.text = SpannableStringBuilder().append(spannedRed).append(spannedSo)
        setSupportActionBar(toolbar)
    }

    private fun setupTabLayout() {
        tabLayout.setBackgroundColor(getColor(R.color.colorPrimary))
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE)
        tabLayout.setupWithViewPager(contentPager)
        tabLayout.isTabIndicatorFullWidth = false

        teams.forEach { team -> tabLayout.addTab(tabLayout.newTab().setText(team)) }
    }

    private fun setupViewPager() {
        val contentPagerAdapter = ContentPagerAdapter(supportFragmentManager, teams)
        contentPager.adapter = contentPagerAdapter
        contentPager.offscreenPageLimit = 2
    }
}
