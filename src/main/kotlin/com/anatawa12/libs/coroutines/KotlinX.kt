package com.anatawa12.libs.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.coroutineContext

/**
 * Created by anatawa12 on 2018/05/01.
 */

/**
 * join to all jobs
 */
suspend fun Iterable<Job>.joinAll() = forEach { it.join() }

suspend fun coroutineScope(): CoroutineScope = CoroutineScope(coroutineContext)
