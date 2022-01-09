package ru.tyupik.vkfriends.ui

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKAuthException
import ru.tyupik.vkfriends.R
import ru.tyupik.vkfriends.databinding.ActivityMainBinding
import com.vk.api.sdk.utils.VKUtils
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
                override fun onLogin(token: VKAccessToken) {
                    Toast.makeText(applicationContext, "LOGIN", Toast.LENGTH_SHORT).show()
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.friendsFragment)
                }

                override fun onLoginFailed(authException: VKAuthException) {
                    Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}