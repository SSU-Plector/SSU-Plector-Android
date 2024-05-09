package com.zucchini.data

import com.sample.network.service.ProjectsService
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsService: ProjectsService,
)
