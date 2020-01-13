package com.amrudesh.appupdater

import android.content.Context
import android.widget.Toast
import com.amrudesh.appupdater.enums.Update

/**
 * Created by Amrudesh Balakrishnan.
 */
class AppUpdater(var context: Context) : AppUpdate {


    val toast = Toast.makeText(context, "Class Invoked", Toast.LENGTH_LONG);


    override fun updateType(update: Update): AppUpdater {
        toast.show()
        return this
    }

    override fun setCheckSum(checksum: String) {

    }

    override fun setVersion(version: Long) {

    }

    override fun alertBoxPositionButton(pressed: Boolean) {

    }

    override fun alertBoxNegativeButton(pressed: Boolean) {

    }

}