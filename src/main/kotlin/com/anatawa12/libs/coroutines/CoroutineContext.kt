package com.anatawa12.libs.coroutines

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.intrinsics.suspendCoroutineOrReturn

/**
 * Created by anatawa12 on 2018/02/25.
 */
/**
 * return [Continuation.context]
 */
suspend fun coroutineContext() = suspendCoroutineOrReturn<CoroutineContext> { continuation -> continuation.context }
