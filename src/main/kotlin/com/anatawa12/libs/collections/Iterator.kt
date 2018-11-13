package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/14.
 */

/**
 * make Iterator Iterable
 */
fun <T> Iterator<T>.asIterable(): Iterable<T> = object : Iterable<T> {
	override fun iterator(): Iterator<T> {
		return this@asIterable
	}
}

/**
 * make Iterator Iterable
 */
@JvmName("asMutableIterable")
fun <T> MutableIterator<T>.asIterable(): MutableIterable<T> = object : MutableIterable<T> {
	override fun iterator(): MutableIterator<T> {
		return this@asIterable
	}
}
