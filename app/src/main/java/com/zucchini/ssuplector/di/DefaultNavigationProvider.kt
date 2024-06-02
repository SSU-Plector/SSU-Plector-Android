package com.zucchini.ssuplector.di

import android.content.Context
import android.content.Intent
import com.zucchini.auth.LoginActivity
import com.zucchini.common.NavigationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultNavigationProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : NavigationProvider {

    override fun toDevInfo(): Intent {
        TODO("Not yet implemented")
    }

    override fun toProjects(): Intent {
        TODO("Not yet implemented")
    }

    override fun toMyPage(): Intent {
        TODO("Not yet implemented")
    }

    override fun toLogin(): Intent {
        return Intent(context, LoginActivity::class.java)
    }
}
