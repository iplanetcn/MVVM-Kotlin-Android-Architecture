package com.task.ui.component.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.task.R
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.giphyGallery.GiphyGalleryActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override val layoutId: Int
        get() = R.layout.splash_layout

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.gotoMainScreen.observe(this, Observer {
            startActivity<GiphyGalleryActivity>()
            finish()
        })
        splashViewModel.sleepAndGO()
    }
}