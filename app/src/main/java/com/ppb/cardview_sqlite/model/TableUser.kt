package com.ppb.cardview_sqlite.model

import android.provider.BaseColumns

object TableUser {
    class UserInput: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASSWORD = "password"
            val COL_USERNAME = "username"
            val COL_FULLNAME = "fullname"
        }
    }
}