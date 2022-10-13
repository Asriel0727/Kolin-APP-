package com.example.a1091613

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class WishActivity : AppCompatActivity() {
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)
        //取得資料庫實體
        dbrw = PokeDBHelper(this).writableDatabase
        //宣告 Adapter 並連結 ListView
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.listView).adapter = adapter
        //設定監聽器
        setListener()
    }
    override fun onDestroy() {
        dbrw.close() //關閉資料庫
        super.onDestroy()
    }
    //設定監聽器
    private fun setListener() {
        val ed_pokeId = findViewById<EditText>(R.id.ed_pokeId)
        val ed_poke = findViewById<EditText>(R.id.ed_poke)
        findViewById<Button>(R.id.btn_insert).setOnClickListener {
            //判斷是否有填入Id或名稱
            if (ed_pokeId.length() < 1 || ed_poke.length() < 1)
                showToast("欄位請勿留空")
            else
                try {
                    //新增一筆紀錄於 pokemonTable 資料表
                    dbrw.execSQL(
                        "INSERT INTO pokemonTable(pokeId, poke) VALUES(?,?)",
                        arrayOf(ed_pokeId.text.toString(),
                            ed_poke.text.toString())
                    )
                    showToast("新增:${ed_pokeId.text},名稱:${ed_poke.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }
        }

        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            //判斷是否有填入Id
            if (ed_pokeId.length() < 1)
                showToast("書名請勿留空")
            else
                try {
                    //從 pokemonTable 資料表刪除相同Id的紀錄
                    dbrw.execSQL("DELETE FROM pokemonTable WHERE pokeId LIKE '${ed_pokeId.text}'")
                    showToast("刪除:${ed_pokeId.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
        }
        findViewById<Button>(R.id.btn_query).setOnClickListener {
            //若無輸入Id則 SQL 語法為查詢全部Id，反之查詢該Id資料
            val queryString = if (ed_pokeId.length() < 1)
                "SELECT * FROM pokemonTable"
            else
                "SELECT * FROM pokemonTable WHERE pokeId LIKE '${ed_pokeId.text}'"
            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料
            showToast("共有${c.count}筆資料")
            for (i in 0 until c.count) {
                //加入新資料
                items.add("Id:${c.getInt(0)}\t\t\t\t 名稱:${c.getString(1)}")
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        }
    }
    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    //清空輸入的書名與價格
    private fun cleanEditText() {
        findViewById<EditText>(R.id.ed_pokeId).setText("")
        findViewById<EditText>(R.id.ed_poke).setText("")
    }
}