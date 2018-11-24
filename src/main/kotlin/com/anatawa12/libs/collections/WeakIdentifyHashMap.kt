package com.anatawa12.libs.collections

import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

@Suppress("unused")
/**
 * Created by anatawa12 on 2018/06/13.
 */

/**
 * Hash table based implementation of the Map interface, with weak keys, using reference-equality in place of object-equality when comparing keys
 */
class WeakIdentifyHashMap<K : Any,V> @JvmOverloads constructor(initialCapacity: Int = 16, private val loadFactor: Float = 0.75f) : MutableMap<K,V> {
	constructor (map: Map<K, V>) : this((map.size / 0.75).toInt()) {
		for ((k, v) in map) {
			put(k, v)
		}
	}

	private var array: Array<Entry?> = arrayOfNulls(initialCapacity)
	private val referenceQueue = ReferenceQueue<K>()
	private fun K.hash() = System.identityHashCode(this)
	override var size: Int = 0
		private set

	private inner class Entry(key: K, var value: V) : WeakReference<K>(key, referenceQueue) {
		val hash = key.hash()
		var isRemoved = false
		var next: Entry? = null
	}

	override fun clear() {
		for (i in array.indices) array[i] = null
	}

	override fun put(key: K, value: V): V? {
		removes()
		size++
		val (index, prev, cur) = find(key)
		if (cur != null) {
			val result = cur.value
			cur.value = value
			return result
		} else if (prev == null) {
			array[index] = Entry(key, value)
		} else {
			prev.next = Entry(key, value)
		}
		resize()
		return null
	}

	override fun putAll(from: Map<out K, V>) = from.forEach { (key, value) -> put(key, value) }

	override fun remove(key: K): V? {
		removes()
		val (index, prev, cur) = find(key)
		if (cur != null) remove(index, prev, cur)
		return cur?.value
	}

	override fun remove(key: K, value: V): Boolean {
		removes()
		val (index, prev, cur) = find(key)
		if (cur != null && cur.value == value) {
			remove(index, prev, cur)
			return true
		} else {
			return false
		}
	}

	override fun containsKey(key: K) = find(key).third === key

	override fun containsValue(value: V): Boolean {
		for (cur in array) {
			@Suppress("NAME_SHADOWING")
			var cur = cur ?: continue
			while (true) {
				if (cur.value == value) return true
				cur = cur.next ?: break
			}
		}
		return false
	}

	override fun get(key: K): V? = find(key).third?.value

	override fun isEmpty() = size == 0



	override val entries: MutableSet<MutableMap.MutableEntry<K, V>> = Entries()
	private inner class Entries : AbstractMutableSet<MutableMap.MutableEntry<K, V>>() {
		override val size: Int
			get() = this@WeakIdentifyHashMap.size

		override fun add(element: MutableMap.MutableEntry<K, V>): Boolean = throw UnsupportedOperationException()

		override fun clear() {
			this@WeakIdentifyHashMap.clear()
		}

		override fun remove(element: MutableMap.MutableEntry<K, V>): Boolean {
			val (index, prev, cur) = find(element.key)
			if (cur != null && cur.value == element.value) {
				remove(index, prev, cur)
				return true
			} else {
				return false
			}
		}

		override fun contains(element: MutableMap.MutableEntry<K, V>): Boolean {
			val (_, _, cur) = find(element.key)
			return if (cur == null) false else cur.value == element.value
		}

		override fun isEmpty(): Boolean = this@WeakIdentifyHashMap.isEmpty()



		override fun iterator(): MutableIterator<MutableMap.MutableEntry<K, V>> = Iterator()

		private inner class Iterator: MutableIterator<MutableMap.MutableEntry<K, V>> {
			var nextIndex = 0
			var nextPrev: Entry? = null
			var nextCur: Entry? = null
				set(value) {
					field = value
					curK = value?.get()
				}
			var curK: K? = null

			init {
				removes()
				nextCur = array[nextIndex]
				while (nextCur == null) {
					nextIndex++
					if (nextIndex >= array.size) break
					nextCur = array[nextIndex]
					while (nextCur != null && curK == null) {
						nextCur = nextCur!!.next
					}
				}
			}

			override fun hasNext(): Boolean = nextCur != null

			var index: Int by Delegates.notNull()
			var prev: Entry? = null
			var cur: Entry? = null
			lateinit var k: K

			override fun next(): MutableMap.MutableEntry<K, V> {
				removes()
				val cur = nextCur ?: throw NoSuchElementException()
				k = curK!!
				val k = k
				index = nextIndex
				this.cur = cur
				this.prev = nextPrev
				nextPrev = nextCur
				nextCur = nextCur!!.next
				while (nextCur == null || curK == null) {
					nextIndex++
					nextCur = array[nextIndex]
					while (nextCur != null && curK == null) {
						nextCur = nextCur!!.next
					}
				}
				return object : MutableMap.MutableEntry<K, V> {
					override val key = k
					override val value: V
						get() = cur.value

					override fun setValue(newValue: V): V {
						val result = cur.value
						cur.value = newValue
						return result
					}
				}
			}

			override fun remove() {
				removes()
				remove(index, prev, cur ?: error("removed"))
				cur = null
			}
		}
	}

	override val keys: MutableSet<K> = object : AbstractMutableSet<K>() {
		override val size: Int
			get() = this@WeakIdentifyHashMap.size

		override fun add(element: K): Boolean = throw UnsupportedOperationException()

		override fun clear() {
			this@WeakIdentifyHashMap.clear()
		}

		override fun remove(element: K): Boolean {
			val (index, prev, cur) = find(element)
			if (cur != null) {
				remove(index, prev, cur)
				return true
			} else {
				return false
			}
		}

		override fun contains(element: K): Boolean {
			val (_, _, cur) = find(element)
			return cur != null
		}

		override fun isEmpty(): Boolean = this@WeakIdentifyHashMap.isEmpty()

		override fun iterator(): MutableIterator<K> = object : MutableIterator<K> {
			val iterator = entries.iterator()

			override fun hasNext(): Boolean = iterator.hasNext()

			override fun next(): K = iterator.next().key

			override fun remove() = iterator.remove()
		}
	}

	override val values: MutableCollection<V> = object : AbstractMutableCollection<V>() {
		override val size: Int
			get() = this@WeakIdentifyHashMap.size

		override fun add(element: V): Boolean = throw UnsupportedOperationException()

		override fun clear() {
			this@WeakIdentifyHashMap.clear()
		}

		override fun isEmpty(): Boolean = this@WeakIdentifyHashMap.isEmpty()

		override fun iterator(): MutableIterator<V> = object : MutableIterator<V> {
			val iterator = entries.iterator()

			override fun hasNext(): Boolean = iterator.hasNext()

			override fun next(): V = iterator.next().value

			override fun remove() = iterator.remove()
		}
	}

	private fun resize() {
		if (size * loadFactor >= array.size) {
			val newSize = array.size * 2
			val newAry = arrayOfNulls<Entry>(newSize)
			for (entry in array) {
				var cur = entry
				while (cur != null) {
					val k = cur.get()
					if (k != null) {
						val index = cur.hash % newSize
						var prev: Entry? = null
						var nCur: Entry? = newAry[index]!!
						while (nCur != null) {
							prev = nCur
							nCur = nCur.next
						}
						if (prev == null) {
							newAry[index] = cur
						} else {
							prev.next = cur
						}
					}
					cur = cur.next
				}
			}
			array = newAry
		}
	}

	private fun removes() {
		while (true) {
			val e = (referenceQueue.poll() ?: return) as WeakIdentifyHashMap<*, *>.Entry
			e.isRemoved = true
			val index = e.hash % array.size
			var prev: Entry? = null
			var cur = array[index]
			while (cur != null) {
				if (e === cur) remove(index, prev, cur)
				prev = cur
				cur = cur.next
			}
		}
	}

	private fun remove(index: Int, prev: Entry?, cur: Entry) {
		size--
		if (prev == null) array[index] = cur.next
		else prev.next = cur.next
	}


	private fun find(k: K): Triple<Int, Entry?, Entry?> {
		val hash = k.hash()
		val index = hash % array.size
		var prev: Entry? = null
		var cur = array[index]
		while (cur != null) {
			if (cur.get() === k) {
				return Triple(index, prev, cur)
			}
			prev = cur
			cur = cur.next
		}
		return Triple(index, prev, null)
	}
}
