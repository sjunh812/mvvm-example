package org.sjhstudio.viewmodelexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import org.sjhstudio.viewmodelexample.adapter.FollowViewPagerAdapter
import org.sjhstudio.viewmodelexample.databinding.ActivityExampleBinding
import org.sjhstudio.viewmodelexample.viewmodel.MainFollowViewModel

class ExampleActivity: BaseActivity() {

    private lateinit var binding: ActivityExampleBinding
    private lateinit var followVpAdapter: FollowViewPagerAdapter

    private val vm by viewModels<MainFollowViewModel>()

    private var tabTitles = arrayListOf<String>()
    private var emptyFragment = EmptyFragment()
    private var exFragment = ExampleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_example)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.exViewModel = vm
        vm.followCount.observe(this) {
            binding.tabLayout.getTabAt(1)?.text = "$it 팔로잉"
        }

        setViewPagerAndTab()
    }

    fun setViewPagerAndTab() {
        followVpAdapter = FollowViewPagerAdapter(this).apply {
            fragmentItems = arrayListOf(emptyFragment, exFragment)
        }
        binding.viewPager.adapter = followVpAdapter
        binding.viewPager.currentItem = 1

        tabTitles = arrayListOf("팔로워", "팔로잉")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }
}