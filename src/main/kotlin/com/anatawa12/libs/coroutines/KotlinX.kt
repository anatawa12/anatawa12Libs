package com.anatawa12.libs.coroutines

import kotlinx.coroutines.experimental.Job

/**
 * Created by anatawa12 on 2018/05/01.
 */

/**
 * join to all jobs
 */
suspend fun Iterable<Job>.joinAll() = forEach { it.join() }
