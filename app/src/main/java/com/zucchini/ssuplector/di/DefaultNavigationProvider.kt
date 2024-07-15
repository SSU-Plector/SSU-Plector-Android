package com.zucchini.ssuplector.di

import android.content.Context
import android.content.Intent
import com.zucchini.auth.LoginActivity
import com.zucchini.common.NavigationProvider
import com.zucchini.projects.MainActivity
import com.zucchini.submit.developer.SubmitDevActivity
import com.zucchini.submit.project.SubmitProjectFindDevActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultNavigationProvider
    @Inject
    constructor(
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

        override fun toLogin(): Intent = Intent(context, LoginActivity::class.java)

        override fun toSubmitDev(): Intent = Intent(context, SubmitDevActivity::class.java)

        override fun toFindDev(): Intent = Intent(context, SubmitProjectFindDevActivity::class.java)

        override fun toMain(): Intent = Intent(context, MainActivity::class.java)
    }
