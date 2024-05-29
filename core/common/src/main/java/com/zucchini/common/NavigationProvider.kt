package com.zucchini.common

import android.content.Intent

interface NavigationProvider {
    fun toDevInfo(): Intent
    fun toProjects(): Intent
    fun toMyPage(): Intent

    fun toLogin(): Intent
}
