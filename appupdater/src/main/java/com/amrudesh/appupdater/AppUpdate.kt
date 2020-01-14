package com.amrudesh.appupdater

import androidx.annotation.RequiresFeature
import com.amrudesh.appupdater.enums.UpdateType
import com.amrudesh.appupdater.enums.UpdateView

/**
 * Created by Amrudesh Balakrishnan.
 */
interface AppUpdate {

    fun updateType(update: UpdateType): AppUpdater
    fun updateViewType(update: UpdateView): AppUpdater
    fun setServerURL(url: String)
    fun setCheckSum(checksum: String)
    fun setVersion(version: Long)
    fun alertBoxPositionButton(pressed: Boolean)
    fun create(): AppUpdater
}