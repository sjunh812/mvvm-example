package org.sjhstudio.viewmodelexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel
 *
 * <주의사항>
 *     뷰모델은 반드시 Activity, Fragment, Context 를
 *     참조해선 안된다!
 *     뷰모델은 위의 것들과 상이한 생명주기를 가지므로, 만약
 *     위의 것들을 참조하면 이미 죽은 객체들을 계속 가지게됨으로
 *     '메모리 누수' 발생!!
 *
 *     (단, applicationContext 는 애플리케이션 자체 생명주기를 가지므로
 *     참조해도 괜찮다!
 *     = 이경우 ViewModel 이 아닌 AndroidViewModel 를 상속받는다.)
 */
class MainViewModel: ViewModel() {

    private var _data = MutableLiveData<Int>()
    val data: LiveData<Int>
        get() = _data

    init {
        _data.value = 100
    }

    fun increase() {
        _data.value = _data.value?.plus(1)
    }

    fun decrease() {
        _data.value = _data.value?.minus(1)
    }
}