package com.anatawa12.libs.collections.hockables

/**
 * Created by anatawa12 on 2018/03/17.
 */

/**
 * A MutableIterator that can make hook when modification.
 */
class HookAbleMutableIterator<E>(val base: MutableIterator<E>, val hocks: MutableIteratorHock<E>) : MutableIterator<E> {
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
}

open class MutableIteratorHock<in E>(
		val remove: (E)->Unit = {}
)
