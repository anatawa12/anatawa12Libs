package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/25.
 */

/**
 * An bimap that if there isn't value, use [initializer] to initialize map
 */
class InitBiMap<K : Any, V : Any>(val map: MutableBiMap<K, V> = MutableHashBiMap(), private val initializer: (K)->V) : BiMap<K, V> by map, NotNullBiMap<K, V> {
	@Deprecated("", ReplaceWith("InitBiMap(map, initializer)"))
	constructor(initializer: ()->V, map: MutableBiMap<K, V> = MutableHashBiMap()) : this(map, { initializer() })

	override fun get(key: K): V = map.getOrPut(key) { initializer(key) }

	override fun toString(): String = map.toString()

	override fun hashCode(): Int = map.hashCode()

	override fun equals(other: Any?): Boolean = map.equals(other)
}

/**
 * An bimap that if there isn't value, use [initializer] to initialize map
 */
class InitMutableBiMap<K : Any, V : Any> constructor(val map: MutableBiMap<K, V> = MutableHashBiMap(), private val initializer: (K)->V) : MutableBiMap<K, V> by map, NotNullMutableBiMap<K, V> {
	@Deprecated("", ReplaceWith("InitMutableBiMap(map, initializer)"))
	constructor(initializer: ()->V, map: MutableBiMap<K, V> = MutableHashBiMap()) : this(map, { initializer() })

	override fun get(key: K): V = map.getOrPut(key) { initializer(key) }

	override fun toString(): String = map.toString()

	override fun hashCode(): Int = map.hashCode()

	override fun equals(other: Any?): Boolean = map.equals(other)
}
