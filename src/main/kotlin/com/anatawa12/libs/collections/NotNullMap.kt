package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/31.
 */

/**
 * a map that don't return null
 */
interface NotNullMap<K, out V : Any> : Map<K, V> {
	override fun get(key: K): V
}

/**
 * a map that don't return null
 */
interface NotNullMutableMap<K, V : Any> : NotNullMap<K, V>, MutableMap<K, V> {
}
