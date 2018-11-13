package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/25.
 */

/**
 * An map that if there isn't value, use [initializer] to initialize map
 */
class InitMap<K, V : Any>(val map: MutableMap<K, V> = mutableMapOf(), private val initializer: (K)->V) : Map<K, V> by map, NotNullMap<K, V> {
	constructor(initializer: ()->V, map: MutableMap<K, V> = mutableMapOf()) : this(map, { _ -> initializer() })

	override fun get(key: K): V = map.getOrPut(key) { initializer(key) }

	override fun toString(): String = map.toString()

	override fun hashCode(): Int = map.hashCode()

	override fun equals(other: Any?): Boolean = map.equals(other)
}

/**
 * An map that if there isn't value, use [initializer] to initialize map
 */
class InitMutableMap<K, V : Any>(val map: MutableMap<K, V> = mutableMapOf(), private val initializer: (K)->V) : MutableMap<K, V> by map, NotNullMutableMap<K, V> {
	constructor(initializer: ()->V, map: MutableMap<K, V> = mutableMapOf()) : this(map, { _ -> initializer() })

	override fun get(key: K): V = map.getOrPut(key) { initializer(key) }

	override fun toString(): String = map.toString()

	override fun hashCode(): Int = map.hashCode()

	override fun equals(other: Any?): Boolean = map.equals(other)
}