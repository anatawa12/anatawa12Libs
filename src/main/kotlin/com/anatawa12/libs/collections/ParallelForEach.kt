package com.anatawa12.libs.collections

import com.anatawa12.libs.collections.synchronized.synchronized
import com.anatawa12.libs.coroutines.coroutineScope
import com.anatawa12.libs.coroutines.joinAll
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

//import kotlin.coroutines.coroutineContext

/**
 * Created by anatawa12 on 2018/05/01.
 */


/**
 * Returns a list containing the results of applying the given [transform] function
 * to each element in the original collection. call [transform] in parallel.
 */
suspend inline fun <T, R> Iterable<T>.asyncParallelMap(crossinline transform: (T) -> R): List<R> {
	// kotlin.collections.collectionSizeOrDefault
	val defaultSize = if (this is Collection<*>) this.size else 10
	val destination = ArrayList<Deferred<R>>(defaultSize)

	for (item in this) {
		destination.add(coroutineScope().async { transform(item) })
	}

	return destination.map { it.await() }
}

/**
 * Returns a list containing the results of applying the given [transform] function
 * to each element in the original collection. call [transform] in parallel.
 */
fun <T, R> Iterable<T>.parallelMap(
		numThreads: Int = Runtime.getRuntime().availableProcessors() - 2,
		exec: ExecutorService = Executors.newFixedThreadPool(numThreads),
		transform: (T) -> R): List<R> {

	// default size is just an inlined version of kotlin.collections.collectionSizeOrDefault
	val defaultSize = if (this is Collection<*>) this.size else 10
	val destination = ArrayList<Future<R>>(defaultSize).synchronized()

	for (item in this) {
		destination.add(exec.submit(Callable { transform(item) }))
	}

	return destination.map { it.get() }
}

/**
 * Returns a list containing only the non-null results of applying the given [transform] function
 * to each element in the original collection. call [transform] in parallel.
 */
suspend inline fun <T, R: Any> Iterable<T>.asyncParallelMapNotNull(crossinline transform: (T) -> R?): List<R> {

	// kotlin.collections.collectionSizeOrDefault
	val defaultSize = if (this is Collection<*>) this.size else 10
	val destination = ArrayList<Deferred<R?>>(defaultSize)

	for (item in this) {
		destination.add(coroutineScope().async { transform(item) })
	}

	return destination.mapNotNull { it.await() }
}

/**
 * Returns a list containing only the non-null results of applying the given [transform] function
 * to each element in the original collection. call [transform] in parallel.
 */
fun <T, R: Any> Iterable<T>.parallelMapNotNull(
		numThreads: Int = Runtime.getRuntime().availableProcessors() - 2,
		exec: ExecutorService = Executors.newFixedThreadPool(numThreads),
		transform: (T) -> R?): List<R> {

	// default size is just an inlined version of kotlin.collections.collectionSizeOrDefault
	val defaultSize = if (this is Collection<*>) this.size else 10
	val destination = ArrayList<Future<R?>>(defaultSize).synchronized()

	for (item in this) {
		destination.add(exec.submit(Callable { transform(item) }))
	}

	return destination.mapNotNull { it.get() }
}

/**
 * Performs the given [transform] on each element in parallel.
 */
suspend inline fun <T> Iterable<T>.asyncParallelForEach(crossinline transform: suspend (T) -> Unit) {
	val defaultSize = if (this is Collection<*>) this.size else 10
	val jobs = ArrayList<Job>(defaultSize)
	for (item in this) {
		jobs.add(coroutineScope().launch { transform(item) })
	}
	jobs.joinAll()
}
