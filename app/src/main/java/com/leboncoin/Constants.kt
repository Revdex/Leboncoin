package com.leboncoin

object Constants {
    const val APP_NAME = "com.leboncoin"
    const val LOG_TAG = "$APP_NAME:"
    const val IS_DEBUG = true

    object Network {
        const val BASE_URL = "https://static.leboncoin.fr/img/shared/"
        const val DISK_CACHE_SIZE = 10 * 1024 * 1024 // 10MB
        const val CONNECT_TIMEOUT: Long = 60 // 1 minutes
        const val READ_TIMEOUT: Long = 60 // 1 minutes
    }

    object Persistence {
        const val DB_NAME = "$APP_NAME.db"
        const val DB_VERSION = 1
        const val SP_NAME = APP_NAME + "SharedPreferences"
    }

}