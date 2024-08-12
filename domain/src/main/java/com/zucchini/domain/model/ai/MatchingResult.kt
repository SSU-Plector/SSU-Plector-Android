package com.zucchini.domain.model.ai

data class MatchingResult(
    val id: Int,
    val name: String,
    val email: String,
    val part1: String,
    val part2: String,
    val description: String
)
