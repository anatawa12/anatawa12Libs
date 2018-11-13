package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/25.
 */
/**
 * if there is no element, throw [NoSuchElementException]
 */
class WarpGetThrowMap<K, out V : Any>(val map: Map<K, V>) : Map<K, V> by map, NotNullMap<K, V> {
	override fun get(key: K): V = map.getOrThr(key)
}

/**
 * if there is no element, throw [NoSuchElementException]
 */
class WarpGetThrowMutableMap<K, V : Any>(val map: MutableMap<K, V>) : MutableMap<K, V> by map, NotNullMutableMap<K, V> {
	override fun get(key: K): V = map.getOrThr(key)
}