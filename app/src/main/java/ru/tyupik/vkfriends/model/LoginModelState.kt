package ru.tyupik.vkfriends.model

data class LoginModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val success: Boolean = false
)
