package com.anatawa12.libs.collections.hockables

/**
 * Created by anatawa12 on 2018/03/17.
 */

/**
 * A MutableList that can make hook when modification.
 */
class HookAbleMutableList<E>(private val base: MutableList<E>, private val hocks: MutableListHock<E>) : MutableList<E> {
	override fun addAll(elements: Collection<E>): Boolean {
		return elements
				.map { element -> add(element) }
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
				.map { element -> remove(element) }
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

	override fun get(index: Int): E {
		return base.get(index)
	}

	override fun indexOf(element: E): Int {
		return base.indexOf(element)
	}

	override fun lastIndexOf(element: E): Int {
		return base.lastIndexOf(element)
	}

	override fun add(index: Int, element: E) {
		return base.add(index, element).also { hocks.add(element) }
	}

	override fun addAll(index: Int, elements: Collection<E>): Boolean {
		return base.addAll(index, elements).also { if (it) elements.forEach { hocks.add(it) } }
	}

	override fun listIterator(): MutableListIterator<E> {
		return HookAbleMutableListIterator(base.listIterator(), MutableListIteratorHock(add = hocks.add, set = hocks.add, remove = hocks.remove));
	}

	override fun listIterator(index: Int): MutableListIterator<E> {
		return HookAbleMutableListIterator(base.listIterator(index), MutableListIteratorHock(add = hocks.add, set = hocks.add, remove = hocks.remove));
	}

	override fun removeAt(index: Int): E {
		return base.removeAt(index).also { hocks.remove(it) }
	}

	override fun set(index: Int, element: E): E {
		return base.set(index, element).also { hocks.set(index, element) }
	}

	override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
		return HookAbleMutableList(base.subList(fromIndex, toIndex), hocks)
	}

	override fun add(element: E): Boolean {
		return base.add(element).also { if (it) hocks.add(element) }
	}
}

open class MutableListHock<in E> (
		add: (E)->Unit = {},
		val set: (Int, E)->Unit = {_, _ ->},
		clear: ()->Unit = {},
		remove: (E)->Unit = {}) : MutableCollectionHock<E>(add, clear, remove)