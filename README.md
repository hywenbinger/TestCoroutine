#### Kotlin中开启一个后台任务的三种方式：
1. 使用协程开启后台任务
```kotlin
GlobalScope.launch {
    Utils.log("GlobalScope, ${Thread.currentThread().name}")
}
```

2. 使用Thread开启后台任务
```kotlin
Thread {
    Utils.log("Thread, ${Thread.currentThread().name}")
}.start()
```

3. 使用Kotlin中封装的thread方法，开启后台任务
```kotlin
thread {
    Utils.log("thread, ${Thread.currentThread().name}")
}
```

---

#### 协程的作用
* **挂起** 和 **恢复** 可以控制执行流程的转移
* 异步逻辑可以用同步的形式写出
* 同步代码比异步代码更灵活，更容易实现复杂业务

---

#### Kotlin协程只是个“线程框架”？
* 运行在线程上的框架不一定就是“线程框架”
* 支持线程切换的框架不一定就是“线程框架”

---

#### 挂起函数
* 挂起函数是以 **suspend** 关键词修饰的函数
* 挂起函数只能在 **其他挂起函数** 或 **协程** 中调用
* 挂起函数调用时包含了协程 **挂起** 的语义
* 挂起函数返回时包含了协程 **恢复** 的语义

---