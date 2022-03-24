/*
 * Copyright (c) 2020.
 * PT. Company
 */

package com.company.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToHome()
    }

    private fun goToHome() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        if (getIntent().extras != null) {
            val clickAction = getIntent().extras!!.getString("click_action")
            if (clickAction != null) {
                intent.data = Uri.parse(clickAction)
            }
        }
        startActivity(intent)
        finishAffinity()
    }
}