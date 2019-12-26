package com.task.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]