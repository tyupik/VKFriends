package ru.tyupik.vkfriends.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.tyupik.vkfriends.model.LoginModelState
import java.lang.Exception


class LoginViewModel : ViewModel() {

    private val _dataState = MutableLiveData<LoginModelState>()
    val dataState: LiveData<LoginModelState>
        get() = _dataState

    fun login() {

        try {
            _dataState.value = LoginModelState(loading = true)
            //логин ВК

            Handler(Looper.getMainLooper()).postDelayed({
                _dataState.value = LoginModelState(success = true)
            }, 500)
        } catch (e: Exception) {
            _dataState.value = LoginModelState(error = true)
        }
    }


}