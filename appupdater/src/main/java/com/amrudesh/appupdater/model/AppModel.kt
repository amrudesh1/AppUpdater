package com.amrudesh.appupdater.model

/**
 * Created by Amrudesh Balakrishnan.
 */
data class AppModel(var name: String) {
    var versionName: String = ""
    var versionCode: Int = 0
    var downloadURL: String = ""
}