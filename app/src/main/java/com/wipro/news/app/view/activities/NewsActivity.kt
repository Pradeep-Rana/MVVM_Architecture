package com.wipro.news.app.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.extreme.zebra.R
import com.wipro.news.app.view.adapters.TabsAdapter
import com.google.android.material.tabs.TabLayout

class NewsActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = "Home"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout?.addTab(tabLayout?.newTab()?.setText("Video")!!)
        tabLayout?.addTab(tabLayout?.newTab()?.setText("History")!!)
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabsAdapter(supportFragmentManager, tabLayout?.tabCount!!)
        viewPager?.adapter = adapter

        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    open fun updateTitle(title: String) {
        supportActionBar?.title = title
    }
}
/*, KodeinAware {
    //Dependency Injection
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        observeResponse()
        handleClicks()
    }

    private fun handleClicks() {
        textViewLogin.setOnClickListener {
            if (isInternetAvailable()) {
                textViewLogin.text = getString(R.string.loading)
                viewModel.getVideoList()
            } else
                showToast(getString(R.string.no_internet_connection_message))
        }
    }

    private fun observeResponse() {

    }
}*/