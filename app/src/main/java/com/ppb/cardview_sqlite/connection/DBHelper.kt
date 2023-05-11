package com.ppb.cardview_sqlite.connection

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ppb.cardview_sqlite.model.TableUser
import com.ppb.cardview_sqlite.model.User

class DBHelper(context: Context):
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "Users.db"
        val DATABASE_VERSION = 1

        private val SQL_CREATE_QUERY = "CREATE TABLE " + TableUser.UserInput.TABLE_NAME + " (" + TableUser.UserInput.COL_EMAIL + " VARCHAR(200) PRIMARY KEY, " + TableUser.UserInput.COL_PASSWORD + " TEXT, " + TableUser.UserInput.COL_USERNAME + " VARCHAR(200), " + TableUser.UserInput.COL_FULLNAME + " TEXT)"

        private val SQL_DELETE_QUERY = "DROP TABLE IF EXISTS " + TableUser.UserInput.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(emailIn: String, passwordIn: String, usernameIn: String, fullnameIn: String): Boolean {

        val db = writableDatabase
        val nama_tab = TableUser.UserInput.TABLE_NAME
        val email_tab = TableUser.UserInput.COL_EMAIL
        val password_tab = TableUser.UserInput.COL_PASSWORD
        val username_tab = TableUser.UserInput.COL_USERNAME
        val fullname_tab = TableUser.UserInput.COL_FULLNAME

        val sql =
            "INSERT INTO $nama_tab($email_tab, $password_tab, $username_tab, $fullname_tab) VALUES('$emailIn', '$passwordIn', '$usernameIn', '$fullnameIn')"

        db.execSQL(sql)

        return true
    }

    fun fullData(): ArrayList<User> {

        val users = arrayListOf<User>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("SELECT * FROM " + TableUser.UserInput.TABLE_NAME, null)
        } catch (ex: SQLException) {
            db.execSQL(SQL_CREATE_QUERY)

            return ArrayList()
        }

        var email_tab: String
        var password_tab: String
        var username_tab: String
        var fullname_tab: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                email_tab = cursor.getString(cursor.getColumnIndex(TableUser.UserInput.COL_EMAIL))
                password_tab = cursor.getString(cursor.getColumnIndex(TableUser.UserInput.COL_PASSWORD))
                username_tab = cursor.getString(cursor.getColumnIndex(TableUser.UserInput.COL_USERNAME))
                fullname_tab = cursor.getString(cursor.getColumnIndex(TableUser.UserInput.COL_FULLNAME))

                users.add(User(email_tab, password_tab, username_tab, fullname_tab))
                cursor.moveToNext()
            }
        }

        return users
    }

    fun deleteData(emailIn: String) {

        val db = writableDatabase
        val nama_tab = TableUser.UserInput.TABLE_NAME
        val email_tab = TableUser.UserInput.COL_EMAIL
        val sql = "DELETE FROM $nama_tab WHERE $email_tab = '$emailIn'"

        db.execSQL(sql)
    }


    fun updateData(emailIn: String, passwordIn: String, usernameIn: String, fullnameIn: String) {

        val db = writableDatabase
        val nama_tab = TableUser.UserInput.TABLE_NAME
        val email_tab = TableUser.UserInput.COL_EMAIL
        val password_tab = TableUser.UserInput.COL_PASSWORD
        val username_tab = TableUser.UserInput.COL_USERNAME
        val fullname_tab = TableUser.UserInput.COL_FULLNAME
        val sql =
            "UPDATE $nama_tab SET $username_tab = '$usernameIn', $fullname_tab = '$fullnameIn', $password_tab = '$passwordIn' WHERE $email_tab = '$emailIn'"

        db.execSQL(sql)
    }
}