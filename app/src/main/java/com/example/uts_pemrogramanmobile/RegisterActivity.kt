package com.example.uts_pemrogramanmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"
    private lateinit var ivLogo: ImageView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLoginPrompt: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        ivLogo = findViewById(R.id.ivLogo)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLoginPrompt = findViewById(R.id.tvLoginPrompt)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            Log.d(TAG, "Register button clicked")
            Log.d(TAG, "Name: $name")
            Log.d(TAG, "Email: $email")

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(
                    this,
                    "Semua field harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(
                    this,
                    "Password tidak sama",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            with(sharedPref.edit()) {
                putString("name", name)
                putString("email", email)
                putString("password", password)
                apply()
            }

            Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show()

            Toast.makeText(
                this,
                "Selamat datang, $name!",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvLoginPrompt.setOnClickListener {
            finish()
        }
    }
}