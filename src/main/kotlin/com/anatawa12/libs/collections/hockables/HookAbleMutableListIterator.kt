package com.anatawa12.libs.collections.hockables

/**
 * Created by anatawa12 on 2018/03/17.
 */

/**
 * A MutableListIterator that can make hook when modification.
 */
class HookAbleMutableListIterator<E>(val base: MutableListIterator<E>, private val hocks: MutableListIteratorHock<E>) : MutableListIterator<E> {
	var cur: E? = null
	override fun hasNext(): Boolean = base.hasNext()

	override fun next(): E = base.next().also { cur = it }

	override fun remove() {
		base.remove()
		hocks.remove(cur!!)
	}

	override fun toString(): String = base.toString()

	override fun hashCode(): Int = base.hashCode()

	override fun equals(other: Any?): Boolean = base.equals(other)

	override fun hasPrevious(): Boolean {
		return base.hasPrevious()
	}

	override fun nextIndex(): Int {
		return base.nextIndex()
	}

	override fun previous(): E {
		return base.previous()
	}

	override fun previousIndex(): Int {
		return base.previousIndex()
	}

	override fun add(element: E) {
		return base.add(element).also { hocks.add(element) }
	}

	override fun set(element: E) {
		return base.set(element).also { hocks.set(element) }
	}
}

open class MutableListIteratorHock<in E>(
		val add: (E)->Unit = {},
		val set: (E)->Unit = {},
		remove: (E)->Unit = {}
) : MutableIteratorHock<E>(remove)
