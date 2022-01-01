package ru.tyupik.vkfriends.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tyupik.vkfriends.databinding.FragmentFriendsBinding

class FriendsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFriendsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root

    }
}