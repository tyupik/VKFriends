package ru.tyupik.vkfriends.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import ru.tyupik.vkfriends.R
import ru.tyupik.vkfriends.databinding.FragmentFriendItemBinding
import ru.tyupik.vkfriends.model.FriendModel

class FriendsAdapter(
) : ListAdapter<FriendModel, FriendsAdapter.FriendViewHolder>(FriendDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder =
        FriendViewHolder(
            FragmentFriendItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    object FriendDiffItemCallback : DiffUtil.ItemCallback<FriendModel>() {
        override fun areItemsTheSame(oldItem: FriendModel, newItem: FriendModel): Boolean {
            if (oldItem.javaClass != newItem.javaClass) {
                return false
            }
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FriendModel, newItem: FriendModel): Boolean =
            oldItem == newItem

    }


    class FriendViewHolder(
        private val binding: FragmentFriendItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: FriendModel) {
            binding.apply {

                Glide.with(binding.friendAvatar)
                    .load("${friend.avatar}")
                    .placeholder(R.drawable.ic_photo_camera_40)
                    .error(R.drawable.ic_photo_camera_40)
                    .timeout(10_000)
                    .transform(MultiTransformation(FitCenter(), CircleCrop()))
                    .into(binding.friendAvatar)

                friendTxtCity.text = itemView.context.getString(R.string.friend_no_city)
                friendTxtName.text = "${friend.name} ${friend.surname}"
                friend.city?.let { friendTxtCity.text = it }
                if (friend.isOnline) {
                    friendImgOnline.visibility = View.VISIBLE
                } else {
                    friendImgOnline.visibility = View.GONE
                }
            }

        }

    }
}

