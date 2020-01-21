package com.amrudesh.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.amrudesh.appupdater.AppUpdater
import com.amrudesh.appupdater.enums.JsonKeys
import com.amrudesh.appupdater.enums.UpdateType
import com.amrudesh.appupdater.enums.UpdateView

class MainActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog
    lateinit var appUpdater: AppUpdater
    lateinit var hashMap: HashMap<JsonKeys, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hashMap = HashMap<JsonKeys, String>()
        hashMap.put(JsonKeys.VERSION_CODE, "versionCode")
        hashMap.put(JsonKeys.VERSION, "version")
        hashMap.put(JsonKeys.MD5, "md5sum")
        hashMap.put(JsonKeys.DOWNLOAD_URL, "appUrl")
        hashMap.put(JsonKeys.PACKAGE, "package")



        appUpdater = AppUpdater(this)
        appUpdater.updateViewType(UpdateView.ALERTDIALOG)
            .updateType(UpdateType.IMMEDIATE)
           // .setAlertDialogData("Hi", "Hi", "Hi", "Hi")
            .setMap(hashMap)
            .create()


    }


}
