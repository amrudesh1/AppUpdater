package com.amrudesh.appupdater

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.enums.Update

/**
 * Created by Amrudesh Balakrishnan.
 */
class AppUpdater(var context: Context) : AppUpdate {
    lateinit var alertDialog: AlertDialog.Builder

    override fun updateViewType(update: Update): AppUpdater {
        if (update.name.equals("ALERTDIALOG")) {
            alertDialog = Display().showUpdateAvailableDialog(context, "Hi", "Hi", "Hi")
            alertDialog.show()
        }

        return this
    }

    override fun setCheckSum(checksum: String) {

    }

    override fun setVersion(version: Long) {

    }

    override fun alertBoxPositionButton(pressed: Boolean) {

    }

}