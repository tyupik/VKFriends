package ru.tyupik.vkfriends.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKAuthException
import ru.tyupik.vkfriends.R
import ru.tyupik.vkfriends.databinding.FragmentLoginBinding
import ru.tyupik.vkfriends.viewmodel.LoginViewModel

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

//        val fingerprints =
//            activity?.let { VKUtils.getCertificateFingerprint(requireContext(), it.packageName) }
//        print(fingerprints)

        binding.btnLogin.setOnClickListener {
            this.activity?.let {
                VK.login(
                    it,
                    arrayListOf(VKScope.WALL, VKScope.PHOTOS, VKScope.OFFLINE, VKScope.FRIENDS)
                )
            }
//            object: VKAuthCallback {
//                override fun onLogin(token: VKAccessToken) {
//                    findNavController().navigate(R.id.action_loginFragment_to_friendsFragment)
//                }
//
//                override fun onLoginFailed(authException: VKAuthException) {
//                    TODO("Not yet implemented")
//                }
//            }

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