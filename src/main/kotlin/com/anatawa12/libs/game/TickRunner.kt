package com.anatawa12.libs.game

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.experimental.intrinsics.createCoroutineUnchecked
import kotlin.coroutines.experimental.intrinsics.suspendCoroutineOrReturn

/**
 * Created by anatawa12 on 2018/03/15.
 */
private enum class State {
	Ready,
	Running,
	Yield,
	End
}

fun TickRunner(block: suspend ITickRunner.()->Unit): ITickRunner = TickRunnerImpl(block)

interface ITickRunner {
	/**
	 * if return false, the corutine end. so After return false, throw [IllagleStateException]
	 */
	fun doTick(): Boolean

	/**
	 * call when end the tick
	 */
	suspend fun yield ()
}

private class TickRunnerImpl : ITickRunner, Continuation<Unit> {
	private var state = State.Ready
	private var nextStep: Continuation<Unit>? = null

	constructor(block: suspend ITickRunner.()->Unit) {
		nextStep = block.createCoroutineUnchecked(receiver = this, completion = this)
	}

	/**
	 * if return false, the corutine end. so After return false, throw [IllagleStateException]
	 */
	override fun doTick (): Boolean {
		when(state) {
			State.Yield, State.Ready -> {
				state = State.Running
				try {
					nextStep!!.resume(Unit)
				} catch (e: Throwable) {
					state = State.End
					nextStep = null
					throw e
				}
				when(state) {
					State.Running, State.End -> {
						state = State.End
						return false
					}
					State.Yield -> {
						return true
					}
					else -> error("invaid state")
				}
			}
			State.End -> error("the corutine is ended")
			else -> error("invaid state")
		}
	}

	override suspend fun yield () {
		state = State.Yield
		return suspendCoroutineOrReturn { c ->
			nextStep = c
			COROUTINE_SUSPENDED
		}
	}

	suspend fun weitTicks(count: Int) {
		for (i in 1..count) yield()
	}

	override fun resume(value: Unit) {
		state = State.End
	}

	override fun resumeWithException(exception: Throwable) {
		throw exception
	}

	override val context: CoroutineContext
		get() = EmptyCoroutineContext
}

suspend fun ITickRunner.weitTicks(count: Int) {
	for (i in 1..count) yield()
}