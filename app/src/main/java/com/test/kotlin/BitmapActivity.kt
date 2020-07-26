package com.test.kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_bitmap.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/**
 * 使用协程获取网络图片
 */
class BitmapActivity : AppCompatActivity() {

    private val mImageUrl =
        "https://i0.hdslb.com/bfs/archive/4de86ebf90b044bf9ba2becf042a8977062b3f99.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap)

        CoroutineScope(Dispatchers.Main).launch {
            Log.i("TestKotlin", "当前线程1：${Thread.currentThread().name}")
            val bitmap = suspendGetImage(mImageUrl)
            iv.setImageBitmap(bitmap)
        }

    }

    private suspend fun suspendGetImage(imageUrl: String): Bitmap? = withContext(Dispatchers.IO) {
        Log.i("TestKotlin", "当前线程2：${Thread.currentThread().name}")
        var bitmap: Bitmap? = null
        val connection = URL(imageUrl).openConnection() as HttpURLConnection
        if (connection.responseCode == 200) {
            bitmap = BitmapFactory.decodeStream(connection.inputStream)
        }
        return@withContext bitmap
    }

}
