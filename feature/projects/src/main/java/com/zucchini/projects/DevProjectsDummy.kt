package com.zucchini.projects

import com.zucchini.domain.model.DevDetailProjectInfo
import com.zucchini.feature.projects.R

object DevProjectsDummy {
    val devProjectsInfoList = listOf(
        DevDetailProjectInfo(
            image = com.zucchini.core.designsystem.R.drawable.project_profile_default,
            name = "프로젝트임둥",
            description = "가나다아다ㅏ라ㅏ랒아ㅗㅓ 야호",
            sorted = "서비스",
        ),
        DevDetailProjectInfo(
            image = com.zucchini.core.designsystem.R.drawable.project_profile_default,
            name = "프로젝트임둥3333",
            description = "가나다아다ㅏ라ㅏ랒아ㅗㅓ 야호",
            sorted = "게임",
        ),
        DevDetailProjectInfo(
            image = com.zucchini.core.designsystem.R.drawable.project_profile_default,
            name = "프로젝트임둥222",
            description = "가나다아다ㅏ라ㅏ랒아ㅗㅓ 야호ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            sorted = "IoT",
        ),
    )
}
