package com.anatawa12.libs.coroutines

import com.anatawa12.libs.collections.synchronized.synchronized
import kotlinx.coroutines.sync.Mutex
import java.util.concurrent.locks.Condition
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by anatawa12 on 2018/03/27.
 */

/**
 * [Condition] for [Mutex]
 */
class SuspendCondition (private val mutex: Mutex) {
	var starter = mutableMapOf<Any?, Continuation<Unit>>().synchronized()

	suspend fun await(lock: Any? = null) {
		if (!mutex.isLocked) error("await must be in lock")
		suspendCoroutine<Unit> { cont ->
			starter[lock] = cont
			mutex.unlock(lock)
		}
		mutex.lock(lock)
	}

	fun signal() {
		if (!mutex.isLocked) error("signal must be in lock")
		starter.asSequence().firstOrNull()?.let{ (key, value) ->
			value.resume(Unit)
			starter.remove(key)
		}
	}

	fun signalAll() {
		if (!mutex.isLocked) error("signal must be in lock")
		starter.forEach { (_, value) ->
			value.resume(Unit)
		}
		starter.clear()
	}
}