package org.sjhstudio.viewmodelexample

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sjhstudio.viewmodelexample.adapter.FollowListAdapter
import org.sjhstudio.viewmodelexample.adapter.OnFollowListCallback
import org.sjhstudio.viewmodelexample.databinding.FragmentExampleBinding
import org.sjhstudio.viewmodelexample.model.FollowingData
import org.sjhstudio.viewmodelexample.viewmodel.MainFollowViewModel
import org.sjhstudio.viewmodelexample.viewmodel.FollowViewModel

class ExampleFragment: Fragment() {

    private lateinit var binding: FragmentExampleBinding
    private lateinit var followListAdapter: FollowListAdapter

    private val mainVm by activityViewModels<MainFollowViewModel>()    // ExampleActivity ViewModel(팔로워 수)
    private lateinit var subVm: FollowViewModel  // ExampleFragment ViewModel(FollowingData)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExampleBinding.inflate(inflater, container, false)
        subVm = ViewModelProvider(this)[FollowViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFollowRv()
        followObserving()
    }

    fun setFollowRv() {
        followListAdapter = FollowListAdapter(subVm).apply {
            items = subVm.following.value as ArrayList<FollowingData>
            setOnFollowListCallback(object: OnFollowListCallback {
                override fun onFollowing(pos: Int) {
                    mainVm.increase()
                    subVm.following(pos)
                }

                override fun onUnFollowing(pos: Int) {
                    mainVm.decrease()
                    subVm.unFollowing(pos)
                }
            })
        }

        binding.followListRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(FollowListItemDecoration())
            adapter = followListAdapter
        }
    }

    fun followObserving() {
        subVm.following.observe(viewLifecycleOwner) {
            println("xxx followObserving!!")
            followListAdapter.items = it as ArrayList<FollowingData>
            binding.followListRv.adapter = followListAdapter
        }
    }
}

class FollowListItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = 50
    }
}