package com.taehuniy.common.util

import android.content.Context
import com.orhanobut.hawk.Hawk

object HawkUtil {

    fun init(context: Context) {
        Hawk.init(context).build()
    }

    fun <T> set(key: String, value: T?) = Hawk.put(key, value)

    fun <T> get(key: String): T? = Hawk.get<T>(key)
}