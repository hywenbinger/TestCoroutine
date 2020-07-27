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

#### 协程的分类
* 按调用栈
  * 有栈协程：每个协程会分配单独的调用栈，类似线程的调用栈
    * 可以在任意嵌套函数中挂起，例如Lua Coroutine
  * 无栈协程：不会分配单独的调用栈，挂起点状态通过闭包或对象保存
    * 只能在当前函数中挂起，例如Python Generator
* 按调用关系
  * 对称协程：调度权可以转移给任意协程，协程之间是对等关系
    * 例如Go routine
  * 非对称协程：调度权只能转移给调用自己的协程，协程存在父子关系
    * yield只能将调度权转移给对应的resume，例如Python Generator、Lua
      Coroutine

---

#### 挂起函数
* 挂起函数是以 **suspend** 关键词修饰的函数
* 挂起函数只能在 **其他挂起函数** 或 **协程** 中调用
* 挂起函数调用时包含了协程 **挂起** 的语义
* 挂起函数返回时包含了协程 **恢复** 的语义

#### Kotlin Coroutine的API
* launch(CoroutineContext)：创建一个新的协程(launch函数中的那些代码)，并在指定的线程上运行它
* withContext(CoroutineContext)：挂起函数，切换到指定的线程，并在闭包内的逻辑执行结束之后，自动把线程切回去继续执行
* async(CoroutineContext)：启动一个新的协程(async函数中的那些代码)
* await()：挂起函数，会**挂起**协程，直到协程执行完后**恢复**，并返回协程的返回值
* Dispatchers：调度器，可以将协程限制在一个特定的线程执行
  * Dispatchers.Main：Android中的主线程
  * Dispatchers.IO：适合IO密集型的任务，比如：读写文件，操作数据库以及网络请求
  * Dispatchers.Default：适合CPU密集型的任务，比如计算

---