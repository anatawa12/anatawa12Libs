package com.anatawa12.libs.collections.hockables

/**
 * Created by anatawa12 on 2018/03/17.
 */


/**
 * A MutableSet that can make hook when modification.
 */
class HookAbleMutableSet<E>(private val base: MutableSet<E>, private val hocks: MutableSetHock<E>) : MutableSet<E> {
	override fun add(element: E): Boolean {
		return base.add(element).also { if (it) hocks.add(element) }
	}

	override fun addAll(elements: Collection<E>): Boolean {
		return elements
				.map { element -> base.add(element).also { if (it) hocks.add(element) } }
				.all { it }
	}

	override fun clear() {
		base.clear()
		hocks.clear()
	}

	override fun iterator(): MutableIterator<E> {
		return HookAbleMutableIterator(base.iterator(), hocks)
	}

	override fun remove(element: E): Boolean {
		return base.remove(element).also { if (it) hocks.remove(element) }
	}

	override fun removeAll(elements: Collection<E>): Boolean {
		return elements
				.map { element -> base.remove(element).also { if (it) hocks.remove(element) } }
				.all { it }
	}

	override fun retainAll(elements: Collection<E>): Boolean {
		return base.retainAll(elements)
	}

	override val size: Int
		get() = base.size

	override fun contains(element: E): Boolean = base.contains(element)

	override fun containsAll(elements: Collection<E>): Boolean = base.containsAll(elements)

	override fun isEmpty(): Boolean = base.isEmpty()

	override fun toString(): String = base.toString()

	override fun hashCode(): Int = base.hashCode()

	override fun equals(other: Any?): Boolean = base.equals(other)
}

open class MutableSetHock<in E> (
		add: (E)->Unit = {},
		clear: ()->Unit = {},
		remove: (E)->Unit = {}) : MutableCollectionHock<E>(add, clear, remove)