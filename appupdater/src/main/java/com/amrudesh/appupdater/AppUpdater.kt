package com.amrudesh.appupdater

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.enums.UpdateType
import com.amrudesh.appupdater.enums.UpdateView
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Amrudesh Balakrishnan.
 */
class AppUpdater(var context: Context) : AppUpdate {
    lateinit var alertDialog: AlertDialog
    lateinit var snackbar: Snackbar
    var updateType: UpdateType = UpdateType.IMMEDIATE
    var updateView: UpdateView = UpdateView.ALERTDIALOG
    var utility: Utility = Utility()
    lateinit var url: String


    override fun updateType(update: UpdateType): AppUpdater {
        //Default is Immediate Update
        this.updateType = update
        return this
    }


    override fun updateViewType(update: UpdateView): AppUpdater {
        this.updateView = update
        return this
    }

    override fun setCheckSum(checksum: String) {

    }

    override fun setVersion(version: Long) {

    }

    override fun alertBoxPositionButton(pressed: Boolean) {

    }


    override fun setServerURL(url: String) {
        this.url = url
    }

    override fun create(): AppUpdater {
        Log.i("AppUpdater", "create:" + updateView.name)
        utility.checkforUpdate();


        when (updateView) {
            UpdateView.ALERTDIALOG -> {
                alertDialog =
                    Display().showUpdateAvailableDialog(context, "Hi", "Hi", "Hi").create()
                alertDialog.show()
            }
            UpdateView.SNACKBAR -> {
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
        }
        return this
    }


}