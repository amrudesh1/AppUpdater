package com.amrudesh.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amrudesh.appupdater.AppUpdater

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appUpdater = AppUpdater(this)
        appUpdater.toast.show()


    }
}
