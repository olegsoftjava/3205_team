package com.oleg.ivanov.test3205team.presentation.splash_screen

import android.content.Intent
import android.os.Bundle
import com.oleg.ivanov.test3205team.databinding.ActivitySplashBinding
import com.oleg.ivanov.test3205team.presentation.base.BaseActivity
import com.oleg.ivanov.test3205team.presentation.search_screen.SearchActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this@SplashActivity, SearchActivity::class.java))
        finish()
    }
}