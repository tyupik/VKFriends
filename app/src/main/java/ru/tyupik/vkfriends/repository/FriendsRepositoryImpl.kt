package ru.tyupik.vkfriends.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.internal.ApiCommand
import com.vk.api.sdk.requests.VKRequest
import com.vk.api.sdk.utils.VKUtils
import ru.tyupik.vkfriends.model.FriendModel


class FriendsRepositoryImpl : FriendsRepository {

    private val data: ArrayList<FriendModel> = ArrayList()
    private val rawData: ArrayList<FriendModel> = ArrayList()
    val friendsList = MutableLiveData(data).asFlow()

//    fun getFriends(): LiveData<ArrayList<FriendModel>> = friendsList


    override fun loadFriends() {
        rawData.clear()
        val friend1 = FriendModel(
            1,
            "Иван",
            "Петров",
            "Москва",
            "https://upload.wikimedia.org/wikipedia/ru/8/86/%D0%98%D0%B2%D0%B0%D0%BD_%D0%98%D0%B2%D0%B0%D0%BD%D0%BE%D0%B2%D0%B8%D1%87_%D0%9F%D0%B5%D1%82%D1%80%D0%BE%D0%B2_%28%D0%BF%D0%B5%D0%B2%D0%B5%D1%86%29.jpg",
            true
        )
        val friend2 = FriendModel(
            2,
            "Петр",
            "Иванов",
            null,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Ivanov_Pyotr.jpg/280px-Ivanov_Pyotr.jpg",
            false
        )
        val friend3 = FriendModel(3, "Михаил", "Сергеев", "Челябинск", null, true)
//        data.add(friend1)
//        data.add(friend2)
//        data.add(friend3)
        rawData.add(friend1)
        rawData.add(friend2)
        rawData.add(friend3)
        filter("")
    }

    fun filter(query: String) {
        data.clear()
        rawData.forEach {
            val search = "${it.name} ${it.surname}"
            if (search.contains(query, true)) {
                print (it)
                data.add(it)
            }

//            if (it.name.contains(query, true) || (it.surname.contains(query, true))) {
//                data.add(it)
//            }
        }
    }

}