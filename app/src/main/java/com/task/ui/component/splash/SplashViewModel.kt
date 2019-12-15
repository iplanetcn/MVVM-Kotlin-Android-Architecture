package com.task.ui.component.splash

import androidx.lifecycle.MutableLiveData
import com.task.ui.base.BaseViewModel
import com.task.utils.Constants.INSTANCE.SPLASH_DELAY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashViewModel @Inject
constructor(override val coroutineContext: CoroutineContext) : BaseViewModel(), CoroutineScope {
    var gotoMainScreen = MutableLiveData<Boolean>()
    fun sleepAndGO() {
        GlobalScope.launch {
            delay(SPLASH_DELAY.toLong())
            gotoMainScreen.postValue(true)
        }
    }
}
