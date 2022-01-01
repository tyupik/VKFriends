package ru.tyupik.vkfriends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tyupik.vkfriends.model.FriendModelState
import java.lang.Exception


class LoginViewModel : ViewModel() {

    private val _dataState = MutableLiveData<FriendModelState>()
    val dataState: LiveData<FriendModelState>
        get() = _dataState

    fun login() {
        try {
            _dataState.value = FriendModelState(loading = true)
            //загрузка друзей


            _dataState.value = FriendModelState(success = true)
        } catch (e: Exception) {
            _dataState.value = FriendModelState(error = true)
        }

    }


}