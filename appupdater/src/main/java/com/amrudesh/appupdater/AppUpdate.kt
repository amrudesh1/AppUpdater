package com.amrudesh.appupdater

import com.amrudesh.appupdater.enums.Update

/**
 * Created by Amrudesh Balakrishnan.
 */
interface AppUpdate {

    fun updateType(update: Update): AppUpdater
    fun setCheckSum(checksum: String)
    fun setVersion(version: Long)
    fun alertBoxPositionButton(pressed: Boolean)
    fun alertBoxNegativeButton(pressed: Boolean)
}