package com.example.rickandmorty.login.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmorty.databinding.ActivityLoginBinding
import com.example.rickandmorty.login.domain.utils.isValid
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser
import com.example.rickandmorty.login.presentation.viewmodel.LoginViewModel
import com.example.rickandmorty.main.presentation.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.reflect.cast

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            val email: Email = Email(value = binding.etEmail.text.toString())
            val keypass: Keypass = Keypass(value = binding.etKeypass.text.toString())
            loginViewModel.onLoginOptionSelected(email = email, keypass = keypass)
            subscribeToDataFlows()
        }
    }

    private fun subscribeToDataFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginUser.collect{ user -> handleResult(result = user) }
            }
        }
    }

    private fun handleResult(result: LoginUser?) {
        if (result != null){
            navigateToMainActivity()
        }else {
            println("Error: no user detected, login failed")
            Toast.makeText(this,"Login failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}

