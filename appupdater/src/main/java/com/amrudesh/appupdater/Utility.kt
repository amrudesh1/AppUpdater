package com.amrudesh.appupdater

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.util.Log
import androidx.annotation.IntegerRes
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

    fun checkforUpdate(): ArrayList<AppModel> {
        var arrayList = arrayListOf<AppModel>()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://103.248.116.46:8080/appserveratv/rest/384a8294-e470-4907-8563-75c448d6c3ff/ota/update?romVersion=ALL_ROMS")
            .get()
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("ServerResponse", "onFailure: " + e.message)

            }

            override fun onResponse(call: Call, response: Response) {
                val jsonDataString = response.body?.string()
                lateinit var appModel: AppModel

                val data = JSONObject(jsonDataString)
                if (response.isSuccessful) {
                    if (data != null) {
                        val iterator: Iterator<String> = data.keys()
                        while (iterator.hasNext()) {
                            appModel = AppModel("")
                            val key: String = iterator.next()
                            try {
                                if (data.get(key) is JSONArray) {
                                    val jsonArray = data.getJSONArray(key)
                                    val size = jsonArray.length()
                                    for (i in 0 until size) {
                                        dynamicJSONParser(jsonArray.getJSONObject(i))
                                    }
                                } else if (data.get(key) is JSONObject) {
                                    dynamicJSONParser(data.getJSONObject(key))
                                } else {
                                    if (key.contains("version")) {
                                        Log.i(
                                            "DynamicParser",
                                            "dynamicJSONParser: " + data.getString(key)
                                        )
                                        appModel.versionCode = Integer.parseInt(data.getString(key))
                                    } else if (key.contains("versionCode")) {
                                        Log.i(
                                            "DynamicParser",
                                            "dynamicJSONParser: " + data.getString(key)
                                        )
                                        appModel.versionName = data.getString(key)
                                    } else if (key.contains("download") || data.getString(key).endsWith(
                                            ".apk"
                                        )
                                    ) {
                                        appModel.downloadURL = data.getString(key)
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            arrayList.add(appModel)
                        }
                    }
                    Log.i(
                        "DynamicParser",
                        "dynamicJSONParser: " + BuildConfig.VERSION_CODE + " " + BuildConfig.VERSION_NAME + " " + arrayList.size
                    )
                }
            }
        });

        return arrayList
    }


    fun dynamicJSONParser(data: JSONObject?): String {

        var downloadURL: String? = null
        if (data != null) {
            val iterator: Iterator<String> = data.keys()
            while (iterator.hasNext()) {
                val key: String = iterator.next()

                try {
                    if (data.get(key) is JSONArray) {
                        val jsonArray = data.getJSONArray(key)
                        val size = jsonArray.length()
                        for (i in 0 until size) {
                            dynamicJSONParser(jsonArray.getJSONObject(i))
                        }
                    } else if (data.get(key) is JSONObject) {
                        dynamicJSONParser(data.getJSONObject(key))
                    } else {
                        if (key.contains("version")) {
                            Log.i("DynamicParser", "dynamicJSONParser: " + data.getString(key))
                        } else if (key.contains("versionNumber")) {
                            Log.i("DynamicParser", "dynamicJSONParser: " + data.getString(key))
                        }


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        Log.i(
            "DynamicParser",
            "dynamicJSONParser: " + BuildConfig.VERSION_CODE + " " + BuildConfig.VERSION_NAME
        )
        return "downloadURL"
    }


}