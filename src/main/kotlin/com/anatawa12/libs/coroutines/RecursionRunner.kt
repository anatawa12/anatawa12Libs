package com.anatawa12.libs.coroutines

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.experimental.suspendCoroutine


/**
 * Created by anatawa12 on 2018/07/11.
 */
interface IRecursionRunner<out R, in T>{
	suspend fun call(arg: T): R
}

@Suppress("UNCHECKED_CAST")
private class RecursionRunner<R, T>(val method: suspend (IRecursionRunner<R, T>.(T) -> R)) : IRecursionRunner<R, T>, Continuation<R> {
	var next: Continuation<R>? = null
	var status: Int = START
	var arg: T? = null
	var result: R? = null
	var exception: Throwable? = null

	fun run(inArg: T): R {
		status = START
		@Suppress("UNCHECKED_CAST")
		val func = method as Function3<IRecursionRunner<R, T>, T, Continuation<R>, Any?>
		arg = inArg
		val stack = mutableListOf<Continuation<R>>()
		func(this, arg!!, this)
		while (true) {
			when (status) {
				START -> error("don't suspend without runner")
				CALL -> {
					status = START
					stack.add(next!!)
					next = null
					val result = func(this, arg as T, this)
					if (result != COROUTINE_SUSPENDED) {
						status = RETURND
						this.result = result as R
					}
				}
				RETURND -> {
					status = START
					if (stack.isEmpty()) {
						return result as R
					}
					val last = stack.removeAt(stack.lastIndex)
					last.resume(result as R)
				}
				EXCEPTION -> {
					status = START
					if (stack.isEmpty()) {
						throw exception!!
					}
					val last = stack.removeAt(stack.lastIndex)
					last.resumeWithException(exception!!)
				}
				else -> error("")
			}
		}
	}
	override val context: CoroutineContext = EmptyCoroutineContext

	override fun resume(value: R) {
		status = RETURND
		result = value
	}

	override fun resumeWithException(exception: Throwable) {
		status = EXCEPTION
		this.exception = exception
	}


	override suspend fun call(arg: T): R = suspendCoroutine {
		next = it
		this.arg = arg
		status = CALL
	}

	override fun toString(): String {
		return "RecursionRunner(next=$next, status=$status, arg=$arg, result=$result, exception=$exception)"
	}

	companion object {
		const val START = 0
		const val CALL = 1
		const val RETURND = 2
		const val EXCEPTION = 3
	}
}

/**
 * run recursion function without [StackOverflowError]
 */
fun <R, T> recursionRun(arg: T, method: suspend (IRecursionRunner<R, T>.(T) -> R)) = RecursionRunner(method).run(arg)