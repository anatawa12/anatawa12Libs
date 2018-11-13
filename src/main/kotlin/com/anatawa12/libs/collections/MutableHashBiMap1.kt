package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/11/11.
 */
class MutableHashBiMap1<K, V> @JvmOverloads constructor(initialCapacity: Int = 16, private val loadFactor: Float = 0.75f) : MutableBiMap<K, V> {
	constructor (map: Map<K, V>) : this((map.size / 0.75).toInt()) {
		for ((k, v) in map) {
			put(k, v)
		}
	}

	private inner class Entry(val k: K, val v: V) {
		var kHash = k.hash()
		var vHash = v.hash()
		var kNext: Entry? = null
			private set
		var vNext: Entry? = null
			private set
		var kBefore: Entry? = null
			private set
		var vBefore: Entry? = null
			private set
		var isRemoved = false

		fun swapK(next: Entry) {
			next.kBefore = kBefore
			next.kNext = kNext
			kBefore = null
			kNext = null
		}

		fun addKNext(next: Entry) {
			kNext?.let { next.putKNext(it) }
			putKNext(next)
		}

		fun addKBefore(before: Entry) {
			kBefore?.let { before.putKBefore(it) }
			?: kotlin.run { k2v[kHash] = before }
			putKBefore(before)
		}

		fun putKNext(next: Entry) {
			next.kBefore = this
			kNext?.kBefore = null
			kNext = this
		}

		fun putKBefore(before: Entry) {
			before.kNext = this
			kBefore?.kNext = null
			kBefore = this
		}

		fun swapV(next: Entry) {
			next.vBefore = vBefore
			next.vNext = vNext
			vBefore = null
			vNext = null
		}

		fun addVNext(next: Entry) {
			vNext?.let { next.putVNext(it) }
			putVNext(next)
		}

		fun addVBefore(before: Entry) {
			vBefore?.let { before.putVBefore(it) }
					?: kotlin.run { v2k[vHash] = before }
			putVBefore(before)
		}

		fun putVNext(next: Entry) {
			next.vBefore = this
			vNext?.vBefore = null
			vNext = this
		}

		fun putVBefore(before: Entry) {
			before.vNext = this
			vBefore?.vNext = null
			vBefore = this
		}

		fun remove() {
			if (isRemoved) error("removed")
			isRemoved = true
			if (this.kBefore == null) {
				k2v[this.kHash] = this.kNext
			} else {
				this.kBefore!!.kNext = this.kNext
			}
			if (this.vBefore == null) {
				v2k[this.vHash] = this.vNext
			} else {
				this.vBefore!!.vNext = this.vNext
			}
			changeSize(-1)
		}
	}

	override var size: Int = 0
		private set
	private var capacity = initialCapacity
	private var k2v: Array<Entry?> = arrayOfNulls(initialCapacity)
	private var v2k: Array<Entry?> = arrayOfNulls(initialCapacity)

	private fun changeSize(diff: Int) {
		size += diff
		if (capacity * loadFactor > size) return
		capacity *= 2
		val newK2v = arrayOfNulls<Entry?>(capacity)
		for (entry in k2v) {
			@Suppress("NAME_SHADOWING")
			var entry = entry
			while (entry != null) {
				entry.kHash = entry.k.hash()
				var entry1 = newK2v[entry.kHash]
				if (entry1 == null) {
					newK2v[entry.kHash] = entry
				} else {
					while (entry1!!.kNext != null) {
						entry1 = entry1.kNext!!
					}
					entry1.putKNext(entry)
				}
				entry = entry.kNext
			}
		}
		val newV2k = arrayOfNulls<Entry?>(capacity)
		for (entry in v2k) {
			@Suppress("NAME_SHADOWING")
			var entry = entry
			while (entry != null) {
				entry.vHash = entry.v.hash()
				var entry1 = newV2k[entry.vHash]
				if (entry1 == null) {
					newV2k[entry.vHash] = entry
				} else {
					while (entry1!!.vNext != null) {
						entry1 = entry1.vNext!!
					}
					entry1.putVNext(entry)
				}
				entry = entry.vNext
			}
		}
	}

	private fun Any?.hash() = ((this?.hashCode() ?: 0) % capacity)

	private fun getEntryByKey(key: K, hash: Int = key.hash()): Entry? {
		var entry: Entry? = k2v[hash]
		while (entry != null) {
			if (entry.k == key) return entry
			entry = entry.kNext
		}
		return null
	}

	private fun addEntryToKey(inEntry: Entry) {
		k2v[inEntry.kHash]?.also { it.addKBefore(inEntry) }
				?: kotlin.run { k2v[inEntry.kHash] = inEntry }
	}

	private fun getEntryByValue(value: V, hash: Int = value.hash()): Entry? {
		var entry: Entry? = v2k[hash]
		while (entry != null) {
			if (entry.v == value) return entry
			entry = entry.kNext
		}
		return null
	}

	private fun addEntryToValue(hash: Int, inEntry: Entry) {
		v2k[inEntry.vHash]?.also { it.addVBefore(inEntry) }
				?: kotlin.run { v2k[inEntry.vHash] = inEntry }
	}

	private inner class ForcePut(val key: K?, val value: V?, val entry: Entry)

	private fun forcePut1(key: K, newValue: V): ForcePut {
		val keyHash = key.hash()
		val newValueHash = newValue.hash()
		val newValueEntry = getEntryByValue(newValue)
		val newKeyEntry = getEntryByKey(key, keyHash)
		val newEntry = Entry(key, newValue)
		var oldValue: V? = null
		var oldKey: K? = null
		if (newKeyEntry == null) {
			addEntryToKey(newEntry)
		} else {
			oldValue = newKeyEntry.v
			newKeyEntry.swapK(newEntry)
		}
		if (newValueEntry == null) {
			addEntryToValue(newValueHash, newEntry)
		} else {
			oldKey = newValueEntry.k
			newValueEntry.swapK(newEntry)
		}
		if (newKeyEntry == null && newValue == null) changeSize(1)
		return ForcePut(oldKey, oldValue, newEntry)
	}

	override fun put(key: K, value: V): V? {
		if (containsValue(value)) throw IllegalArgumentException("There is already newValue in this BiMap")
		return forcePut1(key, value).value
	}

	override fun putAll(from: Map<out K, V>) {
		for ((k, v) in from) {
			put(k, v)
		}
	}

	override fun forcePut(key: K, value: V): V? = forcePut1(key, value).value

	private val inverse = Inversed()
	override fun inverse(): MutableBiMap<V, K> = inverse

	override val values: MutableSet<V>
		get() = valuesSet
	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = EntriesSet()
	override val keys: MutableSet<K>
		get() = keySet

	override fun containsKey(key: K): Boolean = getEntryByKey(key) != null

	override fun containsValue(value: V): Boolean = getEntryByValue(value) != null

	override fun get(key: K): V? = getEntryByKey(key)?.v

	override fun isEmpty(): Boolean = size == 0

	override fun clear() {
		for (i in k2v.indices) {
			k2v[i] = null
			v2k[i] = null
		}
	}

	override fun remove(key: K): V? {
		val entry = getEntryByKey(key) ?: return null
		val v = entry.v
		entry.remove()
		return v
	}

	private inner class Inversed() : MutableBiMap<V, K> {
		override fun inverse(): MutableBiMap<K, V> = this@MutableHashBiMap1

		override fun forcePut(key: V, value: K): K? = forcePut1(value, key).key

		override val values: MutableSet<K>
			get() = keySet
		override val entries: MutableSet<MutableMap.MutableEntry<V, K>>
			get() = inversedEntriesSet
		override val keys: MutableSet<V>
			get() = valuesSet
		override val size: Int
			get() = this@MutableHashBiMap1.size

		override fun containsKey(key: V): Boolean = this@MutableHashBiMap1.containsValue(key)

		override fun containsValue(value: K): Boolean  = this@MutableHashBiMap1.containsKey(value)

		override fun get(key: V): K? = getEntryByValue(key)?.k

		override fun isEmpty(): Boolean = this@MutableHashBiMap1.isEmpty()

		override fun clear() = this@MutableHashBiMap1.clear()

		override fun put(key: V, value: K): K? {
			if (containsValue(value)) throw IllegalArgumentException("There is already newValue in this BiMap")
			return forcePut1(value, key).key
		}

		override fun putAll(from: Map<out V, K>) {
			for ((k, v) in from) {
				put(k, v)
			}
		}

		override fun remove(key: V): K? {
			val entry = getEntryByValue(key) ?: return null
			val v = entry.k
			entry.remove()
			return v
		}
	}

	private val inversedEntriesSet = InversedEntriesSet()
	private inner class InversedEntriesSet() : AbstractMutableSet<MutableMap.MutableEntry<V, K>>() {
		override fun add(element: MutableMap.MutableEntry<V, K>): Boolean = throw UnsupportedOperationException("add")

		override fun addAll(elements: Collection<MutableMap.MutableEntry<V, K>>): Boolean = throw UnsupportedOperationException("add")

		override fun clear() = this@MutableHashBiMap1.clear()

		override fun iterator(): MutableIterator<MutableMap.MutableEntry<V, K>> = InversedEntriesIterator()

		override fun remove(element: MutableMap.MutableEntry<V, K>): Boolean {
			val entry = getEntryByValue(element.key) ?: return false
			if (entry.v != element.value) return false
			entry.remove()
			return true
		}

		override fun removeAll(elements: Collection<MutableMap.MutableEntry<V, K>>): Boolean {
			var mod = false
			for (element in elements) {
				mod = remove(element) || mod
			}
			return mod
		}

		override val size: Int
			get() = this@MutableHashBiMap1.size

		override fun contains(element: MutableMap.MutableEntry<V, K>): Boolean {
			val entry = getEntryByValue(element.key) ?: return false
			if (entry.v != element.value) return false
			return true
		}

		override fun isEmpty(): Boolean = this@MutableHashBiMap1.isEmpty()
	}

	private inner class InversedEntriesIterator() : MutableIterator<MutableMap.MutableEntry<V, K>> {
		private var curEntry: Entry? = null
		private var isRemoved = true
		override fun remove() {
			if (isRemoved) throw IllegalStateException()
			if (curEntry == null) throw IllegalStateException()
			isRemoved = true
			curEntry!!.remove()
		}

		private fun findNext(): Entry? {
			var curEntry = curEntry
			if (curEntry == null) {
				for (i in 0 until size) {
					curEntry = k2v[i]
					if (curEntry != null) break
				}
			} else {
				val entry = curEntry
				curEntry = entry.kNext
				if (curEntry == null) {
					for (i in (entry.kHash + 1) until size) {
						curEntry = k2v[i]
						if (curEntry != null) break
					}
				}
			}
			return curEntry
		}

		override fun hasNext(): Boolean {
			return findNext() != null
		}

		override fun next(): MutableMap.MutableEntry<V, K> {
			val curEntry = findNext()
			this.curEntry = curEntry
			if (curEntry == null) throw NoSuchMethodException()
			return  InversedMutableEntry(curEntry)
		}
	}

	private inner class InversedMutableEntry(private var entry: Entry) : MutableMap.MutableEntry<V, K> {
		override val key: V
			get() = checkRemoved(entry.v)
		override val value: K
			get() = checkRemoved(entry.k)

		private inline fun <T> checkRemoved(result: T): T {
			if (entry.isRemoved) error("this entry is not valid")
			return result
		}

		override fun setValue(newValue: K): K {
			checkRemoved(null)
			val oldValue = entry.k
			this.entry = forcePut1(newValue, key).entry
			return oldValue
		}
	}

	private inner class EntriesSet() : AbstractMutableSet<MutableMap.MutableEntry<K, V>>() {
		override fun add(element: MutableMap.MutableEntry<K, V>): Boolean = throw UnsupportedOperationException("add")

		override fun addAll(elements: Collection<MutableMap.MutableEntry<K, V>>): Boolean = throw UnsupportedOperationException("add")

		override fun clear() = this@MutableHashBiMap1.clear()

		override fun iterator(): MutableIterator<MutableMap.MutableEntry<K, V>> = EntriesIterator()

		override fun remove(element: MutableMap.MutableEntry<K, V>): Boolean {
			val entry = getEntryByValue(element.value) ?: return false
			if (entry.k != element.key) return false
			entry.remove()
			return true
		}

		override fun removeAll(elements: Collection<MutableMap.MutableEntry<K, V>>): Boolean {
			var mod = false
			for (element in elements) {
				mod = remove(element) || mod
			}
			return mod
		}

		override val size: Int
			get() = this@MutableHashBiMap1.size

		override fun contains(element: MutableMap.MutableEntry<K, V>): Boolean {
			val entry = getEntryByValue(element.value) ?: return false
			if (entry.k != element.key) return false
			return true
		}

		override fun isEmpty(): Boolean = this@MutableHashBiMap1.isEmpty()
	}

	private inner class EntriesIterator() : MutableIterator<MutableMap.MutableEntry<K, V>> {
		private var curEntry: Entry? = null
		private var isRemoved = true
		override fun remove() {
			if (isRemoved) throw IllegalStateException()
			if (curEntry == null) throw IllegalStateException()
			isRemoved = true
			curEntry!!.remove()
		}

		private fun findNext(): Entry? {
			var curEntry = curEntry
			if (curEntry == null) {
				for (i in 0 until size) {
					curEntry = k2v[i]
					if (curEntry != null) break
				}
			} else {
				val entry = curEntry
				curEntry = entry!!.kNext
				if (curEntry == null) {
					for (i in (entry.kHash + 1) until size) {
						curEntry = k2v[i]
						if (curEntry != null) break
					}
				}
			}
			return curEntry
		}

		override fun hasNext(): Boolean {
			return findNext() != null
		}

		override fun next(): MutableMap.MutableEntry<K, V> {
			val curEntry = findNext()
			this.curEntry = curEntry
			if (curEntry == null) throw NoSuchMethodException()
			return  MutableEntry(curEntry)
		}
	}

	private inner class MutableEntry(private var entry: Entry) : MutableMap.MutableEntry<K, V> {
		override val key: K
			get() = checkRemoved(entry.k)
		override val value: V
			get() = checkRemoved(entry.v)

		private inline fun <T>checkRemoved(result: T): T {
			if (entry.isRemoved) error("this entry is not valid")
			return result
		}

		override fun setValue(newValue: V): V {
			checkRemoved(null)
			val oldValue = entry.v
			this.entry = forcePut1(key, newValue).entry
			return oldValue
		}
	}

	private val valuesSet = ValuesSet()
	private val keySet = KeySet()

	private inner class ValuesSet() : AbstractMutableSet<V>() {
		override fun add(element: V): Boolean = throw UnsupportedOperationException("add")

		override fun addAll(elements: Collection<V>): Boolean = throw UnsupportedOperationException("add")

		override fun clear() = this@MutableHashBiMap1.clear()

		override fun iterator(): MutableIterator<V> = object : MutableIterator<V> {
			private val entriesIterator = EntriesIterator()
			override fun remove() = entriesIterator.remove()

			override fun hasNext(): Boolean = entriesIterator.hasNext()

			override fun next(): V = entriesIterator.next().value
		}

		override fun remove(element: V): Boolean {
			val entry = getEntryByValue(element) ?: return false
			val v = entry.k
			entry.remove()
			return true
		}

		override fun removeAll(elements: Collection<V>): Boolean {
			var mod = false
			for (element in elements) {
				mod = remove(element) || mod
			}
			return mod
		}

		override val size: Int
			get() = this@MutableHashBiMap1.size

		override fun contains(element: V): Boolean = containsValue(element)

		override fun isEmpty(): Boolean = this@MutableHashBiMap1.isEmpty()
	}

	private inner class KeySet() : AbstractMutableSet<K>() {
		override fun add(element: K): Boolean = throw UnsupportedOperationException("add")

		override fun addAll(elements: Collection<K>): Boolean = throw UnsupportedOperationException("add")

		override fun clear() = this@MutableHashBiMap1.clear()

		override fun iterator(): MutableIterator<K> = object : MutableIterator<K> {
			private val entriesIterator = EntriesIterator()
			override fun remove() = entriesIterator.remove()

			override fun hasNext(): Boolean = entriesIterator.hasNext()

			override fun next(): K = entriesIterator.next().key
		}

		override fun remove(element: K): Boolean {
			val entry = getEntryByKey(element) ?: return false
			val v = entry.k
			entry.remove()
			return true
		}

		override fun removeAll(elements: Collection<K>): Boolean {
			var mod = false
			for (element in elements) {
				mod = remove(element) || mod
			}
			return mod
		}

		override val size: Int
			get() = this@MutableHashBiMap1.size

		override fun contains(element: K): Boolean = containsKey(element)

		override fun isEmpty(): Boolean = this@MutableHashBiMap1.isEmpty()
	}
}