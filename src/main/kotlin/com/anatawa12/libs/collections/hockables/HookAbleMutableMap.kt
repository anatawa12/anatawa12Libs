package com.anatawa12.libs.collections.hockables

import com.anatawa12.libs.collections.maped.MappedResultMutableSet

/**
 * Created by anatawa12 on 2018/03/17.
 */

/**
 * A MutableMapthat can make hook when modification.
 */
class HookAbleMutableMap<K, V>(private val base: MutableMap<K, V>, private val hocks: MutableMapHock<K, V>) : MutableMap<K, V> {
	override val size: Int
		get() = base.size

	override fun containsKey(key: K): Boolean = base.containsKey(key)

	override fun containsValue(value: V): Boolean = base.containsValue(value)

	override fun get(key: K): V? = base.get(key)

	override fun isEmpty(): Boolean = base.isEmpty()

	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = MappedResultMutableSet(base.entries, ::Entry)
	override val keys: MutableSet<K>
		get() = HookAbleMutableSet(base.keys, MutableSetHock(add = { throw UnsupportedOperationException() }, clear = hocks.clear, remove = hocks.remove))
	override val values: MutableCollection<V>
		get() = object : AbstractMutableCollection<V>(){
			override val size: Int
				get() = base.size

			override fun add(element: V): Boolean {
				throw UnsupportedOperationException()
			}

			override fun iterator(): MutableIterator<V> = object : MutableIterator<V> {
				val base = this@HookAbleMutableMap.base.iterator()
				var cur: K? = null
				override fun hasNext(): Boolean = base.hasNext()

				override fun next(): V = base.next().also { cur = it.key }.value

				override fun remove() {
					base.remove()
					hocks.remove(cur!!)
				}
			}
		}

	override fun clear() {
		base.clear()
		hocks.clear()
	}

	override fun put(key: K, value: V): V? {
		return base.put(key, value).also { hocks.put(key, value) }
	}

	override fun putAll(from: Map<out K, V>) {
		from.forEach { (k, v) -> put(k, v) }
	}

	override fun remove(key: K): V? {
		return base.remove(key).also{ hocks.remove(key) }
	}

	inner class Entry(val base: MutableMap.MutableEntry<K, V>) : MutableMap.MutableEntry<K, V> {
		override val key: K
			get() = base.key
		override val value: V
			get() = base.value

		override fun setValue(newValue: V): V {
			return base.setValue(newValue).also { put(key, value) }
		}

		override fun equals(other: Any?): Boolean = (other is Map.Entry<*, *>) && key == other.key && value == other.value

		override fun hashCode(): Int {
			return (key?.hashCode() ?: 0) xor (value?.hashCode() ?: 0)
		}
	}

	override fun toString(): String = base.toString()

	override fun hashCode(): Int = base.hashCode()

	override fun equals(other: Any?): Boolean = base.equals(other)
}

class MutableMapHock<in K, in V> (
		val clear: ()->Unit = {},
		val put: (K,V)->Unit = {_,_->},
		val remove: (K)->Unit = {})

