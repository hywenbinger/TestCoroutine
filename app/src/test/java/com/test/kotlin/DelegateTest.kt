package com.test.kotlin

import org.junit.Test

class DelegateTest {

    @Test
    fun test() {
        val tank = Tank(TankProxy())
        tank.move()

        println("--------------------------")

        val tank1 = Tank1(TankProxy())
        tank1.move()

        println("--------------------------")

        val tank2 = Tank2(TankProxy())
        tank2.move()

        println("--------------------------")

        val person = Person()
        person.name = "Wayne"
        println("name is ${person.name}")

    }

}

class Tank(proxy: Movable) : Movable {
    private var proxy: Movable? = proxy
    override fun move() {
        println("Tank moving")
        proxy?.move()
    }
}

class Tank1(proxy: Movable) : Movable by proxy {
    override fun move() {
        println("Tank1 moving")
    }
}

class Tank2(proxy: Movable) : Movable by proxy

class TankProxy : Movable {
    override fun move() {
        println("Tank proxy moving")
    }
}

interface Movable {
    fun move()
}

class Person {
    var name: String = ""
        set(value) {
            println("set name")
            field = value
        }
        get() {
            println("get name")
            return "$field Han"
        }
}
