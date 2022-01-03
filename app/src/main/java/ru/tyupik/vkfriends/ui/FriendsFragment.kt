package ru.tyupik.vkfriends.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import ru.tyupik.vkfriends.R
import ru.tyupik.vkfriends.adapter.FriendsAdapter
import ru.tyupik.vkfriends.databinding.FragmentFriendsBinding
import ru.tyupik.vkfriends.viewmodel.FriendsViewModel

class FriendsFragment : Fragment() {

    private val friendsViewModel = FriendsViewModel()

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

        friendsViewModel.loadFriends()
//        friendsViewModel.filter("")
        val adapter = FriendsAdapter()
        binding.recyclerFriends.adapter = adapter



        binding.txtFriendsSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                friendsViewModel.filter(query.toString())
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                friendsViewModel.filter(query.toString())
            }

            override fun afterTextChanged(query: Editable?) {
//                friendsViewModel.filter(query.toString())
            }
        })

        friendsViewModel.dataState.observe(viewLifecycleOwner, { state ->
            binding.cpiFriends.isVisible = state.loading
            if (state.error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry_loading) {
                        friendsViewModel.loadFriends()
                    }
                    .show()
            }
            if (state.loading) {
                binding.cpiFriends.visibility = View.VISIBLE
            } else {
                binding.cpiFriends.visibility = View.GONE
            }
        })


        lifecycleScope.launchWhenCreated {
            friendsViewModel.data.collectLatest {
                print (it)
                adapter.submitList(it)
            }
        }


        return binding.root

    }
}