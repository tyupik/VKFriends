package ru.tyupik.vkfriends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import ru.tyupik.vkfriends.model.FriendModel
import ru.tyupik.vkfriends.model.FriendModelState
import ru.tyupik.vkfriends.repository.FriendsRepositoryImpl


class FriendsViewModel : ViewModel() {

    private val friendsRepositoryImpl = FriendsRepositoryImpl()

//    val data: LiveData<ArrayList<FriendModel>>
//        get() = friendsRepositoryImpl.friendsList
//            .asLiveData(Dispatchers.Default)

    val data: Flow<ArrayList<FriendModel>> = friendsRepositoryImpl.friendsList
//            .asLiveData(Dispatchers.Default)

//    val data: Flow<ArrayList<FriendModel>>
//        get() = friendsRepositoryImpl.friendsList
//            .flatMapLatest {
//
//            }

    private val _dataState = MutableLiveData<FriendModelState>()
    val dataState: LiveData<FriendModelState>
        get() = _dataState

    fun loadFriends() {
        try {
            _dataState.value = FriendModelState(loading = true)
            friendsRepositoryImpl.loadFriends()
            _dataState.value = FriendModelState(success = true)
        } catch (e: Exception) {
            _dataState.value = FriendModelState(error = true)
        }
    }

    fun filter(query: String) {
        friendsRepositoryImpl.filter(query)
//        data.collectLatest {
//
//        }
//        data = friendsRepositoryImpl.getFriends()
    }
}

