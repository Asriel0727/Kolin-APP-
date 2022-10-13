package com.example.a1091613

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


data class Item(
    val photo: Int, //圖片
    val name: String, //名稱
)

class OrderActivity : AppCompatActivity() {

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val spinnerval = findViewById<Spinner>(R.id.spinner)
        val listMain = findViewById<ListView>(R.id.listMain)
        val count = ArrayList<String>() //儲存購買數量資訊
        val itemMain = ArrayList<Item>()//儲存水果資訊

        val itemMA = arrayListOf("寶可飼料","咖哩飯","極巨湯")
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemMA)
        listMain.adapter = arrayAdapter

        val name1 = "寶可飼料"
        val name2 = "咖哩飯"
        val name3 = "極巨湯"

        val array =
            resources.obtainTypedArray(R.array.MainImages_list) //從 R 類別讀取圖檔

        for (i in 0 until array.length()) {
            val photo = array.getResourceId(i, 0) //水果圖片Id
            if (i == 0) {
                itemMain.add(Item(photo, name1)) //新增水果資訊
            }
            if (i == 1) {
                itemMain.add(Item(photo, name2)) //新增水果資訊
            }
            if (i == 2) {
                itemMain.add(Item(photo, name3)) //新增水果資訊
            }
            count.add("${i + 1}個") //新增可購買數量資訊
        }
        array.recycle() //釋放圖檔資源
        spinnerval.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, count)

        listMain.adapter = MainAdapter(this, itemMain, R.layout.layout)

        val foodItem = findViewById<TextView>(R.id.textView_foodItem)

        listMain.setOnItemClickListener{parent, view, position, id->
            foodItem.text = "${itemMA[position]}"
        }

        //---------------------------------------------------------------------------------------------------

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val listDessert = findViewById<ListView>(R.id.listDessert)
        val countDessert = ArrayList<String>() //儲存購買數量資訊
        val itemDessert = ArrayList<Item>()//儲存水果資訊

        val itemDA = arrayListOf("寶芙蕾","球果搖搖杯","寶芬")
        val arrayAdapterDessert = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemDA)
        listDessert.adapter = arrayAdapter

        val Dessertname1 = "寶芙蕾"
        val Dessertname2 = "球果搖搖杯"
        val Dessertname3 = "寶芬"
        val Dessertarray =
            resources.obtainTypedArray(R.array.DessertImages_list) //從 R 類別讀取圖檔
        for (i in 0 until Dessertarray.length()) {
            val photo = Dessertarray.getResourceId(i, 0) //水果圖片Id
            if (i == 0) {
                itemDessert.add(Item(photo, Dessertname1)) //新增水果資訊
            }
            if (i == 1) {
                itemDessert.add(Item(photo, Dessertname2)) //新增水果資訊
            }
            if (i == 2) {
                itemDessert.add(Item(photo, Dessertname3)) //新增水果資訊
            }
        }
        Dessertarray.recycle() //釋放圖檔資源
        listDessert.adapter = DessertAdapter(this, itemDessert, R.layout.layout)

        val foodItem2 = findViewById<TextView>(R.id.textView_foodItem2)

        listDessert.setOnItemClickListener{parent, view, position, id->
            foodItem2.text = "${itemDA[position]}"
        }

        val btn_dialog = findViewById<Button>(R.id.button_Yes)

        btn_dialog.setOnClickListener{
            AlertDialog.Builder(this).setTitle("選擇食物")
                .setMessage("主食:${foodItem.text}\n數量:${spinnerval.getSelectedItem()}\n甜點:${foodItem2.text}(固定數量為一份)\n口味:${radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text}")
                .setNegativeButton("取消") { dialog, which ->
                    showToast("取消")
                }
                .setPositiveButton("確定"){dialog,which->
                    showToast("達克萊伊吃得很開心")
                    val intent = Intent()
                    intent.setClass(this, DarkraiActivity::class.java)
                    startActivity(intent)
                }.show()
        }

    }

    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

}