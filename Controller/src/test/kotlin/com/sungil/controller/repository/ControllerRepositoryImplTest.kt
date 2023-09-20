package com.sungil.controller.repository

import com.sungil.device.GpsTracker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ControllerRepositoryImplTest {

    private lateinit var gpsTracker: GpsTracker
    private lateinit var controllerRepositoryImpl: ControllerRepositoryImpl

    @Before
    fun setUp() {
        gpsTracker = Mockito.mock(GpsTracker::class.java)
        controllerRepositoryImpl = ControllerRepositoryImpl()
    }

    @Test
    fun testGetDistanceWhenCalledThenGpsTrackerGetTotalDistanceCalled() = runBlockingTest {
        // Arrange
        val expectedDistance: Flow<Float> = Mockito.mock(Flow::class.java) as Flow<Float>
        Mockito.`when`(gpsTracker.getTotalDistance()).thenReturn(expectedDistance)

        // Act
        val actualDistance = controllerRepositoryImpl.getDistance()

        // Assert
        Assert.assertEquals(expectedDistance, actualDistance)
        Mockito.verify(gpsTracker, Mockito.times(1)).getTotalDistance()
    }
}