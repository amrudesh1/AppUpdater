package com.amrudesh.appupdater

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import com.amrudesh.appupdater.enums.JsonKeys
import com.amrudesh.appupdater.model.AppModel
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by Amrudesh Balakrishnan.
 */
class Utility {
    lateinit var appModel: AppModel
    var arrayList = arrayListOf<AppModel>()
    lateinit var hashMap: HashMap<JsonKeys, String>
    var TAG = Utility::class.java.simpleName

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

    fun checkforUpdate(hashMap: HashMap<JsonKeys, String>): ArrayList<AppModel> {
        val client = OkHttpClient()
        this.hashMap = hashMap
        val request = Request.Builder()
            .url("http://103.248.116.46:8080/appserveratv/rest/384a8294-e470-4907-8563-75c448d6c3ff/ota/update?romVersion=ALL_ROMS")
            .get()
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("ServerResponse", "onFailure: " + e.message)

            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonDataString = response.body?.string()
                    dynamicJSONParser(JSONObject(jsonDataString))
                    Log.i(TAG, "OnSuccess:$jsonDataString")


                }

            }

        });

        return arrayList
    }


    fun dynamicJSONParser(data: JSONObject?) {
        var count = 0
        if (data != null) {
            appModel = AppModel("")

            val iterator: Iterator<String> = data.keys()
            while (iterator.hasNext()) {
                val key: String = iterator.next()
                try {

                    if (data.get(key) is JSONArray) {
                        val jsonArray = data.getJSONArray(key)
                        val size = jsonArray.length()
                        for (i in 0 until size - 1) {
                            dynamicJSONParser(jsonArray.getJSONObject(i))
                        }
                    } else if (data.get(key) is JSONObject) {
                        dynamicJSONParser(data.getJSONObject(key))
                    } else {

                        hashMap.forEach { (keyValue, value) ->
                            if (keyValue == JsonKeys.PACKAGE)
                                if (key == value)
                                    appModel.packageName = data.getString(value)

                            if (keyValue == JsonKeys.DOWNLOAD_URL)
                                if (key == value)
                                    appModel.downloadURL = data.getString(value)

                            if (keyValue == JsonKeys.MD5)
                                if (key == value)
                                    appModel.md5Sum = data.getString(value)



                            if (keyValue == JsonKeys.VERSION_CODE) {
                                Log.i(TAG, "VersionCode: " +data.getInt("versionNumber"))
                                if (key == value || key == "versionNumber") {
                                    appModel.versionCode = data.getInt(value)
                                } else {
                                    appModel.versionCode = data.getInt("versionNumber")

                                }
                            }

                            if (keyValue == JsonKeys.VERSION)
                                if (key == value)
                                    appModel.versionName = data.getString(value)


                            setArrayList(appModel)
                            Log.i(TAG, "JSONMAP:${appModel.packageName}")

                        }
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }
    }


    private fun setArrayList(appModel: AppModel) {
        arrayList.clear()
        arrayList.add(appModel)
        for (i in arrayList) {
            Log.i("DynamicParser", "checkforUpdate: " + i.md5Sum)

        }
        Log.i("DynamicParser", "setArrayList: " + arrayList.size)

    }

    fun getHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}