package com.test.kotlin

import org.junit.Test

class ObjectTest {

    @Test
    fun test() {
        val apple = Apple()
        apple.pick()
        apple.collect()
        apple.eat()
    }

}

open class Fruit {
    open fun eat() {
        println("吃水果")
    }
}

interface Pick{
    fun pick()
    fun collect(){
        println("捡起来")
    }
}

class Apple : Fruit(), Pick {
    override fun eat() {
        super.eat()
        println("吃苹果")
    }

    override fun pick() {
        println("摘苹果")
    }

    override fun collect() {
        super.collect()
        println("捡苹果")
    }

}