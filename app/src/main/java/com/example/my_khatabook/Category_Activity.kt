package com.example.my_khatabook

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_khatabook.Adaptor.Category_Adaptor
import com.example.my_khatabook.DBhelper.DBhelper
import com.example.my_khatabook.ModalClass.CategoryModal
import com.example.my_khatabook.databinding.ActivityCategoryBinding

lateinit var categoryBinding: ActivityCategoryBinding

class Category_Activity : AppCompatActivity() {

    var list = arrayListOf<CategoryModal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        var db = DBhelper(this)
        list = db.getCategory()

        intbindding()


    }

    private fun intbindding() {

        var adaptor = Category_Adaptor(this@Category_Activity, list)
        var lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        categoryBinding.rvDatacategory.layoutManager = lm
        categoryBinding.rvDatacategory.adapter = adaptor

        categoryBinding.btnCategory.setOnClickListener {

            var db = DBhelper(this@Category_Activity)

            if (categoryBinding.edtcateroy.text!!.isEmpty())
            {
                categoryBinding.btnCategory.setError("Plz Enter Category")
            } else {
                db.addCategory(categoryBinding.edtcateroy.text.toString())
                finish()
            }
        }
        categoryBinding.btnBack.setOnClickListener {
            finish()
        }
    }
}