package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/31.
 */
/**
 * a map that don't return null
 */
interface NotNullBiMap<K: Any, V : Any> : BiMap<K, V>, NotNullMap<K, V> {
	override fun get(key: K): V
}

/**
 * a map that don't return null
 */
interface NotNullMutableBiMap<K: Any, V : Any> : NotNullBiMap<K, V>, NotNullMutableMap<K, V>, MutableBiMap<K, V> {
}