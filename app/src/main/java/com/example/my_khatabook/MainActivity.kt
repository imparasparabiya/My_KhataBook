package com.example.my_khatabook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_khatabook.Adaptor.Main_DataAdaptor
import com.example.my_khatabook.DBhelper.DBhelper
import com.example.my_khatabook.ModalClass.EntriesAdd_Modal
import com.example.my_khatabook.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var db: DBhelper
    lateinit var mainList: ArrayList<EntriesAdd_Modal>

    override fun onResume() {
        super.onResume()
        mainList = DBhelper(this).getEntries()
        rvDataSet()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //get Data
        db = DBhelper(this)
        mainList = db.getEntries()

        intbindding()
        totalViews()
    }

    private fun totalViews() {
        val income = listOf<EntriesAdd_Modal>()
    }

    private fun intbindding() {

        rvDataSet()

        binding.btnCategory.setOnClickListener {
            var intent = Intent(this, Category_Activity::class.java)
            startActivity(intent)
        }
        binding.btnAddTransaction.setOnClickListener {
            var intent = Intent(this, Entries_add_Activity::class.java)
            startActivity(intent)
        }
        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Click Pennding", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rvDataSet() {
        var adaptor = Main_DataAdaptor(this, mainList)
        var lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rcDataMain.layoutManager = lm
        binding.rcDataMain.adapter = adaptor
    }
}