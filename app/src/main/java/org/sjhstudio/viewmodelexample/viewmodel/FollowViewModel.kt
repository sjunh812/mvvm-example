package org.sjhstudio.viewmodelexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sjhstudio.viewmodelexample.model.FollowingData

class FollowViewModel: ViewModel() {

    private var _following = MutableLiveData<List<FollowingData>>()
    val following: LiveData<List<FollowingData>>
        get() = _following

    init {
        _following.value = arrayListOf(
            FollowingData("97.__.won", "이상원"),
            FollowingData("_smsoooo_119", "송명수"),
            FollowingData("5u._.y0ng", "배수용"),
            FollowingData("hyeonsu._p", "박현수")
        )
    }

    fun unFollowing(pos: Int) {
        _following.value?.get(pos)?.unFollowing = true
    }

    fun following(pos: Int) {
        _following.value?.get(pos)?.unFollowing = false
    }
}