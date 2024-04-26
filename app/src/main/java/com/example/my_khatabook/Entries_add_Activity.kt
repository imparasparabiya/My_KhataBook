package com.example.my_khatabook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.my_khatabook.DBhelper.DBhelper
import com.example.my_khatabook.ModalClass.CategoryModal
import com.example.my_khatabook.ModalClass.EntriesAdd_Modal
import com.example.my_khatabook.databinding.ActivityEntriesAddBinding

lateinit var entriesAddBinding: ActivityEntriesAddBinding

class Entries_add_Activity : AppCompatActivity() {

    lateinit var db: DBhelper
    var id: String? = null
    var categoryList = arrayListOf<CategoryModal>()
    var dataList = arrayListOf<EntriesAdd_Modal>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entriesAddBinding = ActivityEntriesAddBinding.inflate(layoutInflater)
        setContentView(entriesAddBinding.root)

        db = DBhelper(this)
        categoryList = db.getCategory()
        initbindding()
        getEntries()

    }

    private fun getEntries() {
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var note = intent.getStringExtra("note")
        var date = intent.getStringExtra("date")
        id = intent.getStringExtra("id")
        var status = intent.getIntExtra("status", 0)
        var category = intent.getStringExtra("category")

        if (amount != null) {
            entriesAddBinding.btnDelete.visibility = View.VISIBLE
            entriesAddBinding.edtTitle.setText("$title")
            entriesAddBinding.edtAmount.setText("$amount")
            entriesAddBinding.edtNote.setText("$note")
            entriesAddBinding.edtDate.setText("$date")

            var i = 0
            while (i < categoryList.size) {
                if (categoryList[i].name.equals(category)) {
                    entriesAddBinding.spinerView.setSelection(i)
                }
                i++
            }
        }
    }

    private fun initbindding() {
        entriesAddBinding.btnBack.setOnClickListener { finish() }

        //Sppinner Category
        var nameList = arrayListOf<String>()

        categoryList.forEach {
            nameList.add(it.name)
        }

        var arrayAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            nameList
        )
        entriesAddBinding.spinerView.adapter = arrayAdapter

        // Income Buttonr Click
        entriesAddBinding.btnIncome.setOnClickListener {
            var index = entriesAddBinding.spinerView.selectedItemPosition
            var finalId = "0"

            if (id != null) {
                finalId = id!!
            }
            var modal = EntriesAdd_Modal(
                finalId,
                entriesAddBinding.edtTitle.text.toString(),
                entriesAddBinding.edtAmount.text.toString(),
                entriesAddBinding.edtNote.text.toString(),
                entriesAddBinding.edtDate.text.toString(),
                0,
                nameList[index]
            )
            if (id != null) {
                //method Crreate Update Entries ni
                db.updateEntries(modal)
            } else {
                db.addEntries(modal)
            }
            finish()
        }
        //Expense Button Click
        entriesAddBinding.btnExp.setOnClickListener {

            var index = entriesAddBinding.spinerView.selectedItemPosition
            var finalId = "0"

            if (id != null) {
                finalId = id!!
            }
            var modal = EntriesAdd_Modal(
                finalId,
                entriesAddBinding.edtTitle.text.toString(),
                entriesAddBinding.edtAmount.text.toString(),
                entriesAddBinding.edtNote.text.toString(),
                entriesAddBinding.edtDate.text.toString(),
                1,
                nameList[index]
            )
            if (id != null) {
                //method Crreate Update Entries ni
                db.updateEntries(modal)
            } else {
                db.addEntries(modal)
            }
            finish()
        }
        entriesAddBinding.btnDelete.setOnClickListener {
            db.deleteEntries(id!!)
            finish()
        }
    }
}