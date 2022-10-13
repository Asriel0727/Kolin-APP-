package com.example.a1091613

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow


class MainActivity : AppCompatActivity() {


    private lateinit var tv_progress: TextView
    private lateinit var progressBar2: ProgressBar
    private lateinit var ll_progress: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_progress = findViewById(R.id.tv_progress)
        progressBar2 = findViewById(R.id.progressBar2)
        ll_progress = findViewById(R.id.ll_progress)


        findViewById<Button>(R.id.button2).setOnClickListener {
            runCoroutines()
        }

        findViewById<Button>(R.id.buttondb).setOnClickListener {
            val intent3 = Intent()
            intent3.setClass(this, WishActivity::class.java)
            startActivity(intent3)
        }

    }


    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    //用 Coroutines 模擬檢測過程
    private fun runCoroutines() {
        //初始化進度條
        progressBar2.progress = 0
        tv_progress.text = "0%"
        //顯示進度條
        ll_progress.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            var progress = 0
            //建立迴圈執行一百次共延長五秒
            while (progress < 100) {
                //執行緒延遲 50ms 後執行
                delay(50)
                //執行進度更新
                progressBar2.progress = progress
                tv_progress.text = "$progress%"
                //計數加一
                progress++
            }
            ll_progress.visibility = View.GONE

            val intent3 = Intent()
            intent3.setClass(this@MainActivity, DarkraiActivity::class.java)
            startActivity(intent3)
        }
    }
}