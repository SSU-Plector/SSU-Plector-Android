package com.zucchini.ssuplector.di

import android.content.Context
import android.content.Intent
import com.zucchini.ai_members.designer.AiDesignerActivity
import com.zucchini.ai_members.pm.AiPmActivity
import com.zucchini.auth.LoginActivity
import com.zucchini.common.NavigationProvider
import com.zucchini.projects.MainActivity
import com.zucchini.submit.SubmitDevActivity
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

    override fun toSubmitDev(): Intent {
        return Intent(context, SubmitDevActivity::class.java)
    }

    override fun toMain(): Intent {
        return Intent(context, MainActivity::class.java)
    }

    override fun toAiPmMember(): Intent {
        return Intent(context, AiPmActivity::class.java)
    }

    override fun toAiDesignerMembers(): Intent {
        return Intent(context, AiDesignerActivity::class.java)
    }
}
