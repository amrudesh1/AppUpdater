package com.amrudesh.appupdater

import androidx.annotation.RequiresFeature
import com.amrudesh.appupdater.enums.JsonKeys
import com.amrudesh.appupdater.enums.UpdateType
import com.amrudesh.appupdater.enums.UpdateView

/**
 * Created by Amrudesh Balakrishnan.
 */
interface AppUpdate {
    fun updateType(update: UpdateType): AppUpdater
    fun updateViewType(update: UpdateView): AppUpdater
    fun setServerURL(url: String): AppUpdater
    fun setAlertDialogData(
        title: String = " ",
        description: String = " ",
        positiveButtonText: String = " ",
        negativeButtonText: String= " "
    ): AppUpdater

    fun setAlertDialogLogoResource(resourceId: Int)
    fun setMap(hashMap: HashMap<JsonKeys, String>): AppUpdater
    fun create(): AppUpdater

}