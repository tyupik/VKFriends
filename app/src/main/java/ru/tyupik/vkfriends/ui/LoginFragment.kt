package ru.tyupik.vkfriends.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.tyupik.vkfriends.R
import ru.tyupik.vkfriends.databinding.FragmentLoginBinding
import ru.tyupik.vkfriends.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {

//    @Inject
//    lateinit var loginViewModel: LoginViewModel

    private val loginViewModel = LoginViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        binding.btnLogin.setOnClickListener {
            loginViewModel.login()
        }


        loginViewModel.dataState.observe(viewLifecycleOwner, { state ->
            binding.cpiLogin.isVisible = state.loading
            if (state.error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry_loading) {
                        loginViewModel.login()
                    }
                    .show()
            }
            if (state.loading) {
                binding.btnLogin.visibility = View.GONE
                binding.cpiLogin.visibility = View.VISIBLE
            }
            if (state.success) {
                findNavController().navigate(R.id.action_loginFragment_to_friendsFragment)
            }
        })


        return binding.root
    }

}