package com.example.a1091613

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast

class DarkraiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_darkrai)
        //將變數與 XML 元件綁定
        val img_frame = findViewById<ImageView>(R.id.img_frame)
        val btn_start = findViewById<Button>(R.id.btn_touch)
        val btn_stop = findViewById<Button>(R.id.btn_stoptouch)
        val btn_order = findViewById<Button>(R.id.btn_order)
        val btn_talk = findViewById<Button>(R.id.btn_talk)
        val btn_home = findViewById<Button>(R.id.btn_home)

        //將動畫的 drawable 指定為 ImageView 的背景資源
        img_frame.setBackgroundResource(R.drawable.loading_animation)
        btn_start.setOnClickListener {
            //將圖片背景轉為 AnimationDrawable 並執行
            val animation = img_frame.background as AnimationDrawable
            animation.start()
        }

        btn_stop.setOnClickListener {
            //將圖片背景轉為 AnimationDrawable 並停止
            val animation = img_frame.background as AnimationDrawable
            animation.stop()
            showToast("達克萊伊感到很開心")
        }

        btn_order.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, OrderActivity::class.java)
            startActivity(intent)
        }

        btn_talk.setOnClickListener {
            val anim = AlphaAnimation(
                1.0f,
                0.2f
            )
            anim.duration = 1000
            img_frame.startAnimation(anim)

            val random = (Math.random()*5).toInt()
            val randomtext = when(random){
                0 -> "達克萊伊感到很開心"
                1 -> "達克萊伊想再多跟你聊聊"
                2 -> "達克萊伊想跟你玩玩"
                3 -> "達克萊伊有點餓了"
                else -> "達克萊伊有點累了"
            }
            showToast("${randomtext}")
        }

        btn_home.setOnClickListener {
            val Home = Intent()
            Home.setClass(this, MainActivity::class.java)
            startActivity(Home)
        }

    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}