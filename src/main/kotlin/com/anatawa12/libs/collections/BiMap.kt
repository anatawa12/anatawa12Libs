package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/04/29.
 */

/**
 * A bimap (or "bidirectional map") is a map that preserves the uniqueness of its values as well as that of its keys.
 */
interface BiMap<K, V> : Map<K, V> {
	fun inverse(): BiMap<V, K>
	override val values: Set<V>
}

/**
 * A mutable bimap (or "bidirectional map") is a map that preserves the uniqueness of its values as well as that of its keys.
 */
interface MutableBiMap<K, V> : BiMap<K, V>, MutableMap<K, V> {
	override fun inverse(): MutableBiMap<V, K>
	fun forcePut(key: K, value: V): V?
	override val values: MutableSet<V>
}
