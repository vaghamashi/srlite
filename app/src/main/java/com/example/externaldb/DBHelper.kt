package com.example.externaldb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class       DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, "Data.db", null, 1) {

    private val TAG = "DBHelper"
    var context = context
    var DB_NAME = "Data.db"
//    var path = "/data/data/com.example.externaldb/databases/"
    var path = context?.applicationInfo?.dataDir+"/databases/"

    override fun onCreate(p0: SQLiteDatabase?) {

//        writableDatabase
        if (!isDatabaseExist()!!){
            this.readableDatabase
            Log.e(TAG, "onCreate: ===-=-=-=-=-=-=-=-=-=-=-=-=-" )
            copyDataBase()
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    private fun isDatabaseExist(): Boolean? {
        return File(path + DB_NAME).exists()
    }

    private fun copyDatabase() {
        try {
            val inputStream: InputStream = context?.assets?.open("$DB_NAME")!!
            val outputStream: FileOutputStream = FileOutputStream(path + DB_NAME)
            val buffer = ByteArray(8 * 1024)
            var readed: Int
            while (inputStream.read(buffer).also { readed = it } != -1) {
                outputStream.write(buffer, 0, readed)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e(TAG, "copyDatabase: --------- "+e.message )
        }
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val myInput = context!!.assets.open(DB_NAME)
        val outFileName: String = path + DB_NAME
        val myOutput: OutputStream = FileOutputStream(outFileName)
        val buffer = ByteArray(1024)
        var length: Int
        Log.e(TAG, "copyDataBase: -==-=-=-=-=-=-=-=-=-=-=-" )
        while (myInput.read(buffer).also { length = it } > 0) {
            myOutput.write(buffer, 0, length)
        }

        // Close the streams
        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

    fun getShayari(): ArrayList<ShayariModel> {

        var modelList = ArrayList<ShayariModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM myShayari"
        var cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()


        for (i in 0 until cursor.count){

            var id = cursor.getInt(0)
            var shayari = cursor.getString(1)
            var category = cursor.getString(2)

            var model = ShayariModel(id, shayari, category)
            modelList.add(model)
            cursor.moveToNext()
        }
        return modelList
    }

}

