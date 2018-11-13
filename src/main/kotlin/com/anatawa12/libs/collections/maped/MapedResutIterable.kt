package com.anatawa12.libs.collections.maped

/**
 * Created by anatawa12 on 2018/04/14.
 */

/**
 * result is transformed by [transform]
 */
class MappedResultIterable<V>(val base: Iterable<V>, private val transform: (V) -> V) : Iterable<V> {
	override fun iterator(): Iterator<V> = MappedResultIterator(base.iterator(), transform)
}


/**
 * result is transformed by [transform]
 */
class MappedResultMutableIterable<V>(val base: MutableIterable<V>, private val transform: (V) -> V) : MutableIterable<V> {
	override fun iterator(): MutableIterator<V> = MappedResultMutableIterator(base.iterator(), transform)
}
