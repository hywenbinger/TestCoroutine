package com.test.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

/**
 * Coroutine 的基础用法
 */
class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }

    fun click(view: View) {
        test()
    }

    /**
     * 顺序执行ioCode1()、uiCode1()、ioCode2()、uiCode2()
     */
    private fun test() {
        // 传统线程方式的写法，阅读性太差了
        thread {
            ioCode1()
            runOnUiThread {
                uiCode1()
                thread {
                    ioCode2()
                    runOnUiThread {
                        uiCode2()
                    }
                }
            }
        }

        // 协程方式的写法，结构跟简单，也没有回调地狱
        GlobalScope.launch(Dispatchers.Main) {
            ioCodeCoroutine1()
            uiCode1()
            ioCodeCoroutine2()
            uiCode2()
        }

        // 参照协程，自己写的可以切线程的方法
        ioCodeClassic { uiCode1() }

    }

    private fun ioCode1() {
        Utils.log("ioCode1 start, ${Thread.currentThread().name}")
        Thread.sleep(2000)
        Utils.log("ioCode1 end, ${Thread.currentThread().name}")
    }

    private fun uiCode1() {
        Utils.log("uiCode1, ${Thread.currentThread().name}")
    }

    private fun ioCode2() {
        Utils.log("ioCode2 start, ${Thread.currentThread().name}")
        Thread.sleep(2000)
        Utils.log("ioCode2 end, ${Thread.currentThread().name}")
    }

    private fun uiCode2() {
        Utils.log("uiCode2, ${Thread.currentThread().name}")
    }

    private suspend fun ioCodeCoroutine1() {
        withContext(Dispatchers.IO) {
            Utils.log("ioCodeCoroutine1 start, ${Thread.currentThread().name}")
            Thread.sleep(2000)
            Utils.log("ioCodeCoroutine1 end, ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCodeCoroutine2() {
        withContext(Dispatchers.IO) {
            Utils.log("ioCodeCoroutine2 start, ${Thread.currentThread().name}")
            Thread.sleep(2000)
            Utils.log("ioCodeCoroutine2 end, ${Thread.currentThread().name}")
        }
    }

    private fun ioCodeClassic(block: () -> Unit) {
        thread {
            Utils.log("ioCodeClassic start, ${Thread.currentThread().name}")
            Thread.sleep(2000)
            Utils.log("ioCodeClassic end, ${Thread.currentThread().name}")
            runOnUiThread {
                block()
            }
        }
    }


}