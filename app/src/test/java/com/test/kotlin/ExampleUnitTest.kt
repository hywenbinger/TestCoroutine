package com.test.kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun hello(): Unit {
        println("hello ${getName()}")
        println(getUser(18, "wayne"))
        println(getUser(name = "luna", age = 20))
    }

    private fun getName(): String {
        return "wayne"
    }

    var isRight: Boolean? = null
    var str: String? = null

    @Test
    fun elvis() {
        isRight = true
        println(isRight ?: false)
        str = "hello"
        println(str ?: "is null")
    }

    private fun getUser(age: Int, name: String) = "$name age is $age"


    private fun <T> predicate(t: T, magus: (t: T) -> Boolean): Boolean {
        return magus(t)
    }

    @Test
    fun testPredicate() {
        val isEvenNumber = predicate(t = 10, magus = { x: Int -> x % 2 == 0 })
        println(isEvenNumber)
    }

    @Test
    fun testLambda() {
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        println("1 + 2 = ${sum(1, 2)}")

        val total = sum(1, 2) { x, y -> x + y }
        println("1 + 2 = $total")
    }

    private fun sum(x: Int, y: Int, myFun: (x: Int, y: Int) -> Int): Int {
        return myFun(x, y)
    }

    @Test
    fun testFor() {
        for (a in 10 downTo 1 step 2) {
            println(a)
        }
    }

    @Test
    fun testSafe(){
        val s: String? = null
        println(s!!.length)
        println(s.indexOf("h"))
    }

}