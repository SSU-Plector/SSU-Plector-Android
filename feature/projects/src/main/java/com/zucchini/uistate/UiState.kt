package com.zucchini.uistate

sealed interface UiState<out T> {
    object Initial : UiState<Nothing>

    object Loading : UiState<Nothing>

    data class Success<out T>(
        val data: T? = null,
    ) : UiState<T>

    data class Failure(
        val errorMessage: String,
    ) : UiState<Nothing>
}