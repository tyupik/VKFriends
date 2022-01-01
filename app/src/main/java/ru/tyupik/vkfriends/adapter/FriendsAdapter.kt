package ru.tyupik.vkfriends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import ru.tyupik.vkfriends.databinding.FragmentFriendItemBinding
import ru.tyupik.vkfriends.model.FriendModel

class FriendsAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mFriendsList: ArrayList<FriendModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FriendViewHolder(
            FragmentFriendItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mFriendsList.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class FriendViewHolder(
        private val binding: FragmentFriendItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: FriendModel) {

        }

    }
}

