package com.test.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toBasicActivity(view: View) {
        startActivity(Intent(this, BasicActivity::class.java))
    }

    fun toBitmapActivity(view: View) {
        startActivity(Intent(this, BitmapActivity::class.java))
    }

}
