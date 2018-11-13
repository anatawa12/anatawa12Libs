package com.anatawa12.libs.collections.maped

/**
 * Created by anatawa12 on 2018/04/14.
 */

/**
 * result is transformed by [transform]
 */
class MappedResultCollection<V>(val base: Collection<V>, private val transform: (V) -> V) : Collection<V> {
	override val size: Int
		get() = base.size

	override fun contains(element: V): Boolean = base.contains(element)

	override fun containsAll(elements: Collection<V>): Boolean = base.containsAll(elements)

	override fun isEmpty(): Boolean = base.isEmpty()

	override fun iterator(): Iterator<V> = MappedResultIterator(base.iterator(), transform)
}


/**
 * result is transformed by [transform]
 */
class MappedResultMutableCollection<V>(val base: MutableCollection<V>, private val transform: (V) -> V) : MutableCollection<V> {
	override val size: Int
		get() = base.size

	override fun contains(element: V): Boolean = base.contains(element)

	override fun containsAll(elements: Collection<V>): Boolean = base.containsAll(elements)

	override fun isEmpty(): Boolean = base.isEmpty()

	override fun iterator(): MutableIterator<V> = MappedResultMutableIterator(base.iterator(), transform)

	override fun add(element: V): Boolean = base.add(element)

	override fun addAll(elements: Collection<V>): Boolean = base.addAll(elements)

	override fun clear() = base.clear()

	override fun remove(element: V): Boolean = base.remove(element)

	override fun removeAll(elements: Collection<V>): Boolean = base.removeAll(elements)

	override fun retainAll(elements: Collection<V>): Boolean = base.retainAll(elements)
}
