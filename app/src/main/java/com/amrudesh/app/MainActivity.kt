package com.amrudesh.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.AppUpdater
import com.amrudesh.appupdater.Display
import com.amrudesh.appupdater.enums.Update

class MainActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appUpdater = AppUpdater(this)
        appUpdater.updateViewType(Update.ALERTDIALOG)


    }
}
