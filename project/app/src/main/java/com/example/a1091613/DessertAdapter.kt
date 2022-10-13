package com.example.a1091613

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.text.FieldPosition

class DessertAdapter (context: Context, data: ArrayList<Item>, private val layout: Int):
    ArrayAdapter<Item>(context, layout, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //依據傳入的Layout建立畫面
        val view = View.inflate(parent.context, layout, null)
        //依據position 取得對應資料內容
        val item = getItem(position) ?: return view
        //將圖片指派給ImageView呈現
        val img_photo = view.findViewById<ImageView>(R.id.img_photo)
        img_photo.setImageResource(item.photo)

        val tv_msg = view.findViewById<TextView>(R.id.tv_msg)
        tv_msg.text = item.name
        return view
    }
}