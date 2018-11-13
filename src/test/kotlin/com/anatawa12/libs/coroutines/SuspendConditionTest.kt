package com.anatawa12.libs.coroutines

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by anatawa12 on 2018/03/27.
 */

class SuspendConditionTest {
	@Test
	fun test() {
		val buffer = StringBuffer()

		val mutex = Mutex()

		val condition = SuspendCondition(mutex)

		val job0 = launch {
			buffer.append("job0 start\n")
			delay(150)
			mutex.withLock {
				buffer.append("job0 lock, signal\n")
				condition.signal()
				buffer.append("job0 out lock\n")
			}
		}
		Thread.sleep(50)
		val job1 = launch {
			buffer.append("job1 start\n")
			delay(50)
			mutex.withLock {
				buffer.append("job1 lock, await\n")
				condition.await()
				buffer.append("job1 out await\n")
			}
		}
		runBlocking {
			job0.join()
			job1.join()
		}
		println(buffer)
		assertEquals(buffer.toString(), """job0 start
job1 start
job1 lock, await
job0 lock, signal
job0 out lock
job1 out await
""")
	}
}
