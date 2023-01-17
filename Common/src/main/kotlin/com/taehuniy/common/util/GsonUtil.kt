package com.taehuniy.common.util

import com.google.gson.Gson

object GsonUtil {
    inline fun <reified T: Any> String.toDataClass(): T =
       Gson().fromJson(this, T::class.java)

}