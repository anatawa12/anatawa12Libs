package com.anatawa12.libs.concurrent

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Created by anatawa12 on 2017/11/13.
 */

class CountLatch(count: Int = 0) {
	private val lock = ReentrantLock()
	private var isCounted = count != 0
	private val counted = lock.newCondition()
	private val zero = lock.newCondition()
	private val count = AtomicInteger(count)

	private fun updated(data: Int){
		lock.withLock {
			isCounted = true
			counted.signalAll()
			if (data == 0)
				zero.signalAll()
		}
	}

	fun increment(){
		updated(count.incrementAndGet())
	}

	fun decrement(){
		updated(count.decrementAndGet())
	}

	fun add(int: Int){
		updated(count.addAndGet(int))
	}

	fun sub(int: Int){
		updated(count.addAndGet(-int))
	}

	fun awaitZero(){
		lock.withLock {
			while (!isCounted){
				counted.await()
			}
			while (count.get() != 0){
				zero.await()
			}
		}
	}

	fun get() = count.get()
}