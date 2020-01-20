package com.amrudesh.appupdater

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.enums.JsonKeys
import com.amrudesh.appupdater.enums.UpdateType
import com.amrudesh.appupdater.enums.UpdateView
import com.amrudesh.appupdater.model.AppModel
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
    lateinit var objectMap: HashMap<JsonKeys, String>
    lateinit var url: String
    lateinit var appModel: AppModel


    override fun updateType(update: UpdateType): AppUpdater {
        //Default is Immediate Update
        this.updateType = update
        return this
    }


    override fun updateViewType(update: UpdateView): AppUpdater {
        this.updateView = update
        return this
    }

    override fun setMap(hashMap: HashMap<JsonKeys, String>): AppUpdater {
        objectMap = hashMap
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
        var isUpdateAvailable = false
        val appList = utility.checkforUpdate(objectMap)


        Thread.sleep(3000)
        Log.i("AppList", "create: " + appList.size)
        for (data in appList) {
            Log.i(
                "AppList", "create: " + data.md5Sum + " " +
                        data.versionName + " " + data.versionCode + " " + data.downloadURL + " " + data.name + " " + data.packageName
            )
            if (data.versionCode > BuildConfig.VERSION_CODE) {
                isUpdateAvailable = true
                appModel = data
                break
            }





        }
        if (isUpdateAvailable) {
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
        }
        return this


    }


}