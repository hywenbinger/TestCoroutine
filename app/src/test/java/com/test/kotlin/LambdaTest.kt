package com.test.kotlin

import org.junit.Test

class LambdaTest {

    @Test
    fun testNoParam() {
        val test = { println("这是无参的lambda") }
        test()
    }

    @Test
    fun testHaveParam() {
        println("这是有参的lambda")

        val sum: (Int, Int) -> Int = { x, y -> x + y }
        println("1 + 1 = ${sum(1, 1)}")

        //省略了返回类型，推断出的返回值类型不为Unit时，它的返回值即为->符号后面代码的最后一个表达式的类型
        val sub = { x: Int, y: Int -> x - y }
        println("1 - 1 = ${sub(1, 1)}")
    }

    @Test
    fun testFunParam() {
        println("这是高阶函数，lambda作为函数参数")

        val a = 10
        val b = 10

        val add = operation(a, b) { x, y -> x + y }
        println("$a + $b = $add")

        val sub = operation(a, b) { x, y -> x - y }
        println("$a - $b = $sub")

        val value1 = test(1, 1, ::compute)
        println(value1)

        val value2 = test(1, 1) { x, y -> x - y }
        println(value2)
    }

    private fun operation(a: Int, b: Int, result: (Int, Int) -> Int): Int {
        return result(a, b)
    }

    private fun test(a: Int, b: Int, compute: (x: Int, y: Int) -> Int): Int {
        return compute(a, b)
    }

    private fun compute(x: Int, y: Int) = x + y

}