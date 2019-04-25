package com.example.sampletodolist.view.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.sampletodolist.R
import com.example.sampletodolist.common.Constants
import com.example.sampletodolist.common.Preferences
import com.example.sampletodolist.dto.UserDTO
import com.example.sampletodolist.view.login.LoginActivity
import com.example.sampletodolist.module.UserRealmManager
import com.example.sampletodolist.view.main.MainDrawerActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var realmManager = UserRealmManager()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
