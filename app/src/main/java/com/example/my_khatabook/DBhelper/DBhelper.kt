package com.example.my_khatabook.DBhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.my_khatabook.ModalClass.CategoryModal
import com.example.my_khatabook.ModalClass.EntriesAdd_Modal

class DBhelper(context: Context) : SQLiteOpenHelper(context, "KhataBook", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val query =
            "CREATE TABLE InEx (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, amount TEXT, note TEXT, date TEXT,status INTEGER,category TEXT) " // status and category add kari
        val queryCategory =
            "CREATE TABLE Category (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)"
        //DBhelperma Query add
        db!!.apply {
            execSQL(query)
            execSQL(queryCategory)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addCategory(name: String) {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put("name", name)
        db.insert("Category", null, cn)
    }

    @SuppressLint("Range")
    fun getCategory(): ArrayList<CategoryModal> {

        var list = arrayListOf<CategoryModal>()
        val db = readableDatabase
        val query = "SELECT * FROM Category"
        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                var modal = CategoryModal(id, name)
                list.add(modal)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getEntries():ArrayList<EntriesAdd_Modal>{
        var db = readableDatabase
        var query = "SELECT * FROM InEx"
        var cursor = db.rawQuery(query,null)
        var detail = ArrayList<EntriesAdd_Modal>()//*ArrayList Lidhutu
        if (cursor.moveToFirst())
        {
            do {
                    var id = cursor.getString(cursor.getColumnIndex("id"))
                    var title = cursor.getString(cursor.getColumnIndex("title"))
                    var amount = cursor.getString(cursor.getColumnIndex("amount"))
                    var note = cursor.getString(cursor.getColumnIndex("note"))
                    var date = cursor.getString(cursor.getColumnIndex("date"))
                    var category = cursor.getString(cursor.getColumnIndex("category"))
                    var status = cursor.getString(cursor.getColumnIndex("status"))

                    var modal = EntriesAdd_Modal(
                        id,
                        title,
                        amount,
                        note,
                        date,
                        status.toInt(),
                        category
                    )
                    detail.add(modal)
            }while (cursor.moveToNext())
        }
        return detail
    }
    fun addEntries(modal: EntriesAdd_Modal){
        val db = writableDatabase
        val cn = ContentValues()
      //  cn.put("id",modal.id)
        cn.put("title",modal.title)
        cn.put("amount",modal.amount)
        cn.put("note",modal.note)
        cn.put("date",modal.date)
        cn.put("category",modal.category)
        cn.put("status",modal.status)

        db.insert("InEx",null,cn)
    }
    fun updateEntries(modal: EntriesAdd_Modal){
        val db = writableDatabase
        val cn = ContentValues()
      //  cn.put("id",modal.id)
        cn.put("title",modal.title)
        cn.put("amount",modal.amount)
        cn.put("note",modal.note)
        cn.put("date",modal.date)
        cn.put("category",modal.category)
        cn.put("status",modal.status)

        db.update("InEx",cn,"id=?", arrayOf(modal.id))
    }

    fun totalIncomeB(modal: EntriesAdd_Modal){
        val db=writableDatabase
        val cn = ContentValues()

        cn.put("amount",modal.amount)

        db.insert("InEx",null,cn)
    }
    fun totalExpense(modal: EntriesAdd_Modal){

    }
    fun deleteEntries(id: String){
        val db =writableDatabase
        db.delete("InEx","id=?", arrayOf(id))
    }
}