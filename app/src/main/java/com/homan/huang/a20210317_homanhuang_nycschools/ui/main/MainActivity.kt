package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.background.helper.lgd
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.homan.huang.a20210317_homanhuang_nycschools.R
import com.homan.huang.a20210317_homanhuang_nycschools.databinding.ActivityMainBinding
import com.homan.huang.a20210317_homanhuang_nycschools.helper.msg
import com.homan.huang.a20210317_homanhuang_nycschools.ui.main.InfoStatus.*
import com.homan.huang.a20210317_homanhuang_nycschools.ui.main.adapter.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

// check manifests for permissions
private val REQUIRED_PERMISSIONS = arrayOf(
    Manifest.permission.INTERNET,
    Manifest.permission.ACCESS_NETWORK_STATE
)

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // view binding
    private lateinit var binding: ActivityMainBinding
    // view model
    private val mainVM: MainViewModel by viewModels()

    // app permission
    private val reqMultiplePermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            lgd("MainAct-Permission: ${it.key} = ${it.value}")
            if (!it.value) {
                // toast
                msg(this, "Permission: ${it.key} denied!", 1)
                finish()
            }
        }
    }

    // titles for tab layout
    private val TAB_TITLES = arrayOf(
        R.string.tab_title_1,
        R.string.tab_title_2,
        R.string.tab_title_3,
        R.string.tab_title_4,
        R.string.tab_title_5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // check app permissions
        reqMultiplePermissions.launch(REQUIRED_PERMISSIONS)


        //region TABs: controls schools' categories
        val sectionsPagerAdapter = SectionsPagerAdapter(this, TAB_TITLES.size)
        val viewPager: ViewPager2 = binding.viewPager //findViewById
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs //findViewById
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = "${getString(TAB_TITLES[position])}"
        }.attach()
        //endregion

        // select info fragment
        mainVM.display.observe(this, {
            when(it) {
                SCHOOLS -> {
                    binding.viewPager.visibility = View.VISIBLE
                    binding.scoresDisplay.visibility = View.GONE
                    binding.fabSearch.visibility = View.VISIBLE
                }
                SAT_SCORES -> {
                    binding.viewPager.visibility = View.GONE
                    binding.scoresDisplay.visibility = View.VISIBLE
                    binding.fabSearch.visibility = View.GONE
                }
            }
        })

        // Floating button
        val fab: FloatingActionButton = binding.fabSearch
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onBackPressed() {
        // shut off SAT display
        mainVM.updateDisplay()

        // swap tabs
        if (binding.viewPager.currentItem == 0) {
            binding.viewPager.currentItem = TAB_TITLES.size - 1
        } else {
            // Otherwise, select the previous step.
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        }
    }
}