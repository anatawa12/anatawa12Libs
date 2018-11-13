package com.anatawa12.libs.event

import java.io.Closeable

/**
 * Created by anatawa12 on 2018/07/16.
 */
interface IEventRemover : Closeable {
	fun remove()
	override fun close() { remove() }
}