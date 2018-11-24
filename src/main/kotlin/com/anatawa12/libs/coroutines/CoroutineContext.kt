package com.anatawa12.libs.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.coroutineContext

/**
 * Created by anatawa12 on 2018/02/25.
 */
/**
 * return [Continuation.context]
 */
@Deprecated("please use stdlib", ReplaceWith("coroutineContext", "kotlin.coroutines.coroutineContext"))
suspend fun coroutineContext() = coroutineContext
