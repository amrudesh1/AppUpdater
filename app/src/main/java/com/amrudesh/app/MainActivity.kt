package com.amrudesh.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.AppUpdater
import com.amrudesh.appupdater.enums.UpdateView

class MainActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog
    lateinit var appUpdater: AppUpdater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appUpdater = AppUpdater(this)
        appUpdater.updateViewType(UpdateView.ALERTDIALOG)
            .create()


    }



}
