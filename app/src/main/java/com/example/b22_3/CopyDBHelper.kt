package com.example.b22_3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class CopyDBHelper(private val context: Context) {
    companion object {
        private val DB_NAME = "tuhoc.db"
    }

    fun openDataBase(): SQLiteDatabase {
        val dbFile = context.getDatabasePath("tuhoc.db")
        val file = File(dbFile.toString())

        if (file.exists()) {
            Log.e("tuhoc", "file exists")
        } else {
            copyDatabase(dbFile)
        }
        //đường dẫn, mặc định, đọc và ghi
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)

    }

    private fun copyDatabase(dbFile: File?) {
        val openDB = context.assets.open(DB_NAME)
        val outputStream = FileOutputStream(dbFile)
        val buffer = ByteArray(1024)
        while (openDB.read(buffer)>0){
            outputStream.write(buffer)
            Log.wtf("Db","writting")
        }
        outputStream.flush()
        outputStream.close()
        openDB.close()
        Log.wtf("DB","copied")


    }


}