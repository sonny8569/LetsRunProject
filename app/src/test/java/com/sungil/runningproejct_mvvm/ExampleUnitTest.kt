package com.sungil.runningproejct_mvvm

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

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
    fun getLocationTime(){
        val currentTime = LocalDateTime.now()
        println("현재 시간: $currentTime")
    }
}