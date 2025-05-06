package com.example.uts_pemrogramanmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegisterPrompt: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegisterPrompt = findViewById(R.id.tvRegisterPrompt)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter both email and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val savedEmail = sharedPref.getString("email", null)
            val savedPassword = sharedPref.getString("password", null)

            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                navigateToChatList()
            } else {
                Toast.makeText(
                    this,
                    "Email atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        tvRegisterPrompt.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToChatList() {
        val intent = Intent(this, ChatListActivity::class.java)
        startActivity(intent)
        finish()
    }
}