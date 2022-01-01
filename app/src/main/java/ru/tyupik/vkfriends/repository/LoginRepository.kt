package ru.tyupik.vkfriends.repository

interface LoginRepository {
    fun startLoading()
    fun endLoading()
    fun showError()
    fun openFriends()
}