package com.anatawa12.libs.collections

import java.util.*


@Suppress("UNCHECKED_CAST")
/**
 * Created by anatawa12 on 2017/10/14.
 */

/**
 * list and set.
 * when add a element, if there is the element, didn't add.
 */
class MutableListSet<E> (size: Int) : AbstractMutableSet<E>(), MutableSet<E>, MutableList<E> {
	override fun add(index: Int, element: E) {
		throw UnsupportedOperationException("can't use index operation")
	}

	override fun addAll(index: Int, elements: Collection<E>): Boolean {
		throw UnsupportedOperationException("can't use index operation")
	}

	override fun set(index: Int, element: E): E {
		throw UnsupportedOperationException("can't use index operation")
	}

	constructor() : this(16)

	private var data: Array<Any?>
	private var len: Int
	override var size: Int = 0
		private set
		get

	init {
		len = 16
		while (len < size){
			len *= 2
		}
		data = arrayOfNulls(len)
	}

	override fun add(element: E): Boolean {
		forEach { e ->
			if (e == element)
				return false
		}
		val index = toCanNext()
		data[index] = element
		return true
	}

	override fun remove(element: E): Boolean {
		val index = indexOf(element)
		if (index == -1)
			return false

		return true
	}

	override fun get(index: Int): E {
		inIndexOtThr(index)
		return data[index] as E
	}

	override fun equals(other: Any?): Boolean {
		if (other === this)
			return true
		return when (other) {
			is List<*> -> {
				if (other.size != size)
					return false
				indices.none { this[it] != other[it] }
			}
			is Set<*> -> {
				if (other.size != size)
					return false
				other.any { contains(it) }
			}
			else -> false
		}
	}

	override fun hashCode(): Int {
		var result = 0
		for (item in this){
			result = item.safeHash() or (result shl 4)
		}
		return result
	}

	override fun iterator(): MutableIterator<E> {
		return Itr()
	}

	override fun indexOf(element: E): Int {
		indices.forEach { i ->
			if(this[i] == element)
				return i
		}
		return -1
	}

	override fun lastIndexOf(element: E): Int {
		return indexOf(element)
	}

	override fun listIterator(): MutableListIterator<E> {
		return listIterator(0)
	}

	override fun listIterator(index: Int): MutableListIterator<E> {
		return ListItr(index)
	}

	override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
		return SubList(fromIndex, toIndex)
	}

	override fun spliterator(): Spliterator<E> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}


	override fun removeAt(index: Int): E {
		inIndexOtThr(index)
		val result = data[index]
		for (i in index .. (lastIndex-1)){
			data[i] = data[i+1]
		}
		size--
		return result as E
	}

	private fun toCanNext(): Int {
		if (size == len) {
			len *= 2
		}
		data = Array(len) {
			if (it < size) {
				data[it]
			} else {
				null
			}
		}
		size += 1
		return size - 1
	}

	@Suppress("NOTHING_TO_INLINE")
	private inline fun inIndexOtThr(index: Int) = if (index in 0..lastIndex) null else throw IndexOutOfBoundsException("$index")

	private fun E.safeHash(): Int = this?.hashCode() ?: 0

	private fun E.hash(): Int = this.safeHash() and (len - 1)

	inner class SubList(val fromIndex: Int, val toIndex: Int) : AbstractList<E>() {
		private var superSize: Int = this@MutableListSet.size
		override val size: Int
			get() = toIndex - fromIndex

		fun checkForComodification() = if (this.superSize != this@MutableListSet.size) throw ConcurrentModificationException() else null
		override fun get(index: Int): E {
			checkForComodification()
			return this@MutableListSet[fromIndex + index]
		}
	}

	open inner class Itr : MutableIterator<E> {
		protected var size: Int = this@MutableListSet.size
		protected var next: Int
			get() = lastRet + 1
			set(value) { lastRet = value - 1 }
		protected var previous: Int
			get() = lastRet - 1
			set(value) { lastRet = value + 1 }
		protected var lastRet: Int = -1

		fun checkForComodification() = if (this.size != this@MutableListSet.size) throw ConcurrentModificationException("${this.size} != ${this@MutableListSet.size}") else null

		override fun hasNext(): Boolean {
			return size != next
		}

		override fun next(): E {
			checkForComodification()
			val i = next
			if (i >= size)
				throw NoSuchElementException()
			val data = this@MutableListSet.data
			if (i >= data.size)
				throw ConcurrentModificationException()
			next = i + 1
			return data[lastRet] as E
		}

		override fun remove() {
			if (lastRet < 0)
				throw IllegalStateException()
			checkForComodification()

			try {
				this@MutableListSet.removeAt(lastRet)
				next = lastRet
				size--
			} catch (ex: IndexOutOfBoundsException) {
				throw ConcurrentModificationException()
			}
		}
	}

	inner class ListItr(index: Int) : Itr(), MutableListIterator<E> {
		override fun add(element: E) {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override fun set(element: E) {
			lastRet
		}

		init {
			next = index
		}

		override fun nextIndex(): Int {
			return next
		}

		override fun previous(): E {
			checkForComodification()
			val i = lastRet
			if (i <= size)
				throw NoSuchElementException()
			val data = this@MutableListSet.data
			if (i >= data.size)
				throw ConcurrentModificationException()
			previous = i
			return data[lastRet] as E
		}

		override fun previousIndex(): Int {
			if (previous < 0)
				return -1
			return previous
		}

		override fun hasPrevious(): Boolean {
			return previous >= 0
		}
	}
}