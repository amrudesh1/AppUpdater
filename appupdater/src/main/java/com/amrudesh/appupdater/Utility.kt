package com.amrudesh.appupdater

import android.content.Context
import android.content.pm.PackageInfo
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by Amrudesh Balakrishnan.
 */
class Utility {


    fun getAppName(context: Context): String {
        var applicationInfo = context.applicationInfo
        var stringid = applicationInfo.labelRes
        if (stringid == 0) {
            return applicationInfo.nonLocalizedLabel.toString()
        } else {
            return context.getString(stringid)
        }
    }


    fun generateMD5Checksum(target: String): String {
        lateinit var messageDigest: MessageDigest
        try {
            messageDigest = MessageDigest.getInstance("MD5")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        messageDigest.update(target.toByteArray(), 0, target.length)
        var md5: String = BigInteger(1, messageDigest.digest()).toString()
        while (md5.length < 32) {
            md5 = "0" + md5
        }
        return md5
    }


    fun getAppVersion(context: Context): String {
        lateinit var packageInfo: PackageInfo
        lateinit var version: String
        try {
            packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            version = packageInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return version
    }

}