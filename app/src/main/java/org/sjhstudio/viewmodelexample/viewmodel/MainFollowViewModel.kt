package org.sjhstudio.viewmodelexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFollowViewModel: ViewModel() {

    private var _followCount = MutableLiveData<Int>()
    val followCount: LiveData<Int>
        get() = _followCount

    init {
        _followCount.value = 100
    }

    fun increase() {
        _followCount.value = _followCount.value?.plus(1)
    }

    fun decrease() {
        _followCount.value = _followCount.value?.minus(1)
    }
}