package com.anatawa12.libs.collections

import java.util.*

@Suppress("UNCHECKED_CAST")
/**
 * Created by anatawa12 on 2017/09/24.
 */

/**
 * a stack that capacity is constant
 */
class FixedSizeStack<E>(capacity: Int) : AbstractMutableCollection<E>() {

	private val stack: Array<Any?>

	override var size: Int = 0
		get() = field;
		private set(value) { field = value }

	init {
		stack = Array(capacity){null}
		size = 0
	}

	override fun iterator(): MutableIterator<E> {
		return StackIterator()
	}

	override fun hashCode(): Int {
		return stack.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return stack.equals(other)
	}

	fun peek(): E {
		if (isEmpty()){
			throw EmptyStackException()
		}
		return stack[size-1] as E
	}

	fun push(p0: E) {
		stack[size] = p0
		size++
	}

	override fun add(element: E): Boolean {
		push(element)
		return true
	}

	fun pop(): E {
		if (isEmpty()){
			throw EmptyStackException()
		}
		val ret = stack[size-1] as E
		stack[size-1] = null
		size--
		return ret
	}

	fun empty(): Boolean {
		return size == 0
	}

	override fun toString(): String {
		return stack.toString()
	}

	override fun toArray(): Array<Any?> {
		return stack.copyOfRange(0, size)
	}

	private inner class StackIterator<E> : MutableIterator<E>{
		private var stack: Array<Any?>

		private var index: Int
		private var size: Int
		init {
			index = 0
			stack = this@FixedSizeStack.stack
			size = this@FixedSizeStack.size
		}

		var isRemoved = true
		override fun remove() {
			checkAdd()
			if (isRemoved) throw IllegalStateException();
			isRemoved = true
			for (i in index + 1 until index) {
				stack[i-1] = stack[i]
			}
			size--
			this@FixedSizeStack.size--
		}

		override fun next(): E {
			checkAdd()
			if (index == size)
				throw NoSuchElementException()
			val ret = stack[index] as E
			index++
			isRemoved = true
			return ret
		}

		override fun hasNext(): Boolean {
			checkAdd()
			return index != size
		}

		private fun checkAdd(){
			if (!(size == this@FixedSizeStack.size))
				throw ConcurrentModificationException()
		}
	}
}