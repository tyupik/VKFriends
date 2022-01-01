package ru.tyupik.vkfriends.model

data class FriendModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = true,
    val success: Boolean = false
)
