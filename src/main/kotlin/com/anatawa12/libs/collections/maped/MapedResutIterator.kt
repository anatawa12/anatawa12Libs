package com.anatawa12.libs.collections.maped

/**
 * Created by anatawa12 on 2018/04/14.
 */

/**
 * result is transformed by [transform]
 */
class MappedResultIterator<V>(val base: Iterator<V>, private val transform: (V) -> V) : Iterator<V> {
	override fun hasNext(): Boolean = base.hasNext()

	override fun next(): V = transform(base.next())
}


/**
 * result is transformed by [transform]
 */
class MappedResultMutableIterator<V>(val base: MutableIterator<V>, private val transform: (V) -> V) : MutableIterator<V> {
	override fun remove() = base.remove()

	override fun hasNext(): Boolean = base.hasNext()

	override fun next(): V = transform(base.next())
}
