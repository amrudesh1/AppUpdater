package com.amrudesh.appupdater

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog


/**
 * Created by Amrudesh Balakrishnan.
 */
class Display {
    fun showUpdateAvailableDialog(
        context: Context,
        title: String,
        content: String,
        btnPosition: String
    ): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(context)
        val customLayout = LayoutInflater.from(context).inflate(R.layout.update_alert_dialog, null)
        alertDialog.setView(customLayout)
        return alertDialog
    }
}