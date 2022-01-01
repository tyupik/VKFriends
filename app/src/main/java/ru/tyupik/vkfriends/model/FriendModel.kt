package ru.tyupik.vkfriends.model

data class FriendModel(
    val id: Long,
    val name: String,
    val surname: String,
    val city: String?,
    val avatar: String?,
    val isOnline: Boolean
)