package com.zucchini.domain.model.submit

data class SubmitDevInfo(
    val devGithub: String,
    val devUniversity: String,
    val devMajor: String,
    val devIntro: String,
    val kakaoId: String,
    val devStudentNumber: String,
    val devPart1: String,
    val devPart2: String,
    val devTechStackList: List<String>,
    val devLanguageList: List<String>,
    val devCooperationList: List<String>
)
