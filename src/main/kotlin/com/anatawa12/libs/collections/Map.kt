package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/01/25.
 */
/**
 * key is [Pair.first], value is [Pair.second]'s list
 */
fun <K, V> List<Pair<K, V>>.toMapList(): Map<K, List<V>> = toMapList(mutableMapOf())

/**
 * key is [Pair.first], value is [Pair.second]'s list
 */
fun <K, V, M: MutableMap<in K, MutableList<V>>> List<Pair<K, V>>.toMapList(map: M): M {
	for ((k, v) in this){
		map.getOrPut(k) { mutableListOf() }.add(v)
	}
	return map
}

/**
 * key is [Pair.first], value is [Pair.second]'s list
 */
fun <K, V> Sequence<Pair<K, V>>.toMapList(): Map<K, List<V>> = toMapList(mutableMapOf())

/**
 * key is [Pair.first], value is [Pair.second]'s list
 */
fun <K, V, M: MutableMap<in K, MutableList<V>>> Sequence<Pair<K, V>>.toMapList(map: M): M {
	for ((k, v) in this){
		map.getOrPut(k) { mutableListOf() }.add(v)
	}
	return map
}