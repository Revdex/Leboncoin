package com.leboncoin.utils

import android.util.Log
import com.leboncoin.Constants

object Logger {

    fun <T> i(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.i(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> d(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.d(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> v(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.v(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> e(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.e(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> w(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.w(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> wtf(tag: String, msg: T) {
        if (Constants.IS_DEBUG)
            Log.wtf(Constants.LOG_TAG + tag, msg.toString() + "")
    }

    fun <T> i(msg: T) {
        i(Constants.APP_NAME, msg.toString() + "")
    }

    fun <T> d(msg: T) {
        d(Constants.APP_NAME, msg.toString() + "")
    }

    fun <T> v(msg: T) {
        v(Constants.APP_NAME, msg.toString() + "")
    }

    fun <T> e(msg: T) {
        e(Constants.APP_NAME, msg.toString() + "")
    }

    fun <T> w(msg: T) {
        w(Constants.APP_NAME, msg.toString() + "")
    }

    fun <T> wtf(msg: T) {
        wtf(Constants.APP_NAME, msg.toString() + "")
    }

}
