package com.anatawa12.libs.collections.maped

/**
 * Created by anatawa12 on 2018/04/14.
 */

/**
 * result is transformed by [transform]
 */
class MappedResultMap<K, V>(val base: Map<K, V>, private val transform: (V) -> V) : Map<K, V> {
	override val entries: Set<Map.Entry<K, V>>
		get() = MappedResultSet(base.entries) {
			object : Map.Entry<K, V> {
				override val key: K
					get() = it.key
				override val value: V
					get() = transform(it.value)
			}
		}
	override val keys: Set<K>
		get() = base.keys
	override val size: Int
		get() = base.size
	override val values: Collection<V>
		get() = MappedResultCollection(base.values, transform)

	override fun containsKey(key: K): Boolean {
		return base.containsKey(key)
	}

	override fun containsValue(value: V): Boolean {
		return base.containsValue(value)
	}

	override fun get(key: K): V? {
		return base.get(key)?.let(transform)
	}

	override fun isEmpty(): Boolean {
		return base.isEmpty()
	}
}


/**
 * result is transformed by [transform]
 */
class MappedResultMutableMap<K, V>(val base: MutableMap<K, V>, private val mapper: (V) -> V) : MutableMap<K, V> {
	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = MappedResultMutableSet(base.entries) {
			object : MutableMap.MutableEntry<K, V> {
				override fun setValue(newValue: V): V = mapper(it.setValue(newValue))

				override val key: K
					get() = it.key
				override val value: V
					get() = mapper(it.value)
			}
		}
	override val keys: MutableSet<K>
		get() = base.keys
	override val size: Int
		get() = base.size
	override val values: MutableCollection<V>
		get() = MappedResultMutableCollection(base.values, mapper)

	override fun containsKey(key: K): Boolean {
		return base.containsKey(key)
	}

	override fun containsValue(value: V): Boolean {
		return base.containsValue(value)
	}

	override fun get(key: K): V? {
		return base.get(key)?.let(mapper)
	}

	override fun isEmpty(): Boolean {
		return base.isEmpty()
	}

	override fun clear() {
		base.clear()
	}

	override fun put(key: K, value: V): V? {
		return base.put(key, value)?.let { mapper(it) }
	}

	override fun putAll(from: Map<out K, V>) {
		base.putAll(from)
	}

	override fun remove(key: K): V? {
		return base.remove(key)?.let { mapper(it) }
	}
}
