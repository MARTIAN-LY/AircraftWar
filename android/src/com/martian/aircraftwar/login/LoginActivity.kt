package com.martian.aircraftwar.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.martian.aircraftwar.MainActivity
import com.martian.aircraftwar.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /** 用SharedPreferences缓存用户名和密码 */
        preferences = this.getSharedPreferences("userInfo", MODE_PRIVATE)
        val name = preferences.getString("username", null)
        val pass = preferences.getString("password", null)

        /** 初次登录，需要输用户名、密码 */
        if (name == null && pass == null) {
            binding.buttonLogin.setOnClickListener {
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "用户名、密码不能为空", Toast.LENGTH_SHORT).show()
                } else {
                    val edit = preferences.edit()
                    edit.putString("username", username)
                    edit.putString("password", password)
                    edit.commit()
                    Toast.makeText(this, "欢迎你,$username", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    this.finish()
                }
            }
        } else {
            /** 已经登录过，不用再输入用户名、密码 */
            Toast.makeText(this, "欢迎你,$name", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }
}