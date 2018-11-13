package com.anatawa12.libs.collections

import com.anatawa12.libs.collections.hockables.HookAbleMutableSet
import com.anatawa12.libs.collections.hockables.MutableSetHock
import com.anatawa12.libs.collections.maped.MappedResultMutableSet

/**
 * Created by anatawa12 on 2018/11/11.
 */

/**
 * A BiMap backed by two hash map. This implementation allows null keys and values. A HashBiMap and its inverse are both serializable.
 */
class MutableHashBiMap<K, V>(): MutableBiMap<K, V> {
	private val baseMap: MutableMap<K, V> = mutableMapOf()
	private val inverseMap: MutableMap<V, K> = mutableMapOf()

	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = MappedResultMutableSet(baseMap.entries) { base ->
			object : MutableMap.MutableEntry<K, V> {
				override val key: K
					get() = base.key
				override val value: V
					get() = base.value

				override fun setValue(newValue: V): V {
					require(!containsValue(newValue)) { "$value is contains in this map" }
					return base.setValue(newValue)
				}
			}
		}
	override val keys: MutableSet<K>
		get() = HookAbleMutableSet(baseMap.keys, MutableSetHock(clear = {inverseMap.clear()}, remove = { inverseMap.remove(baseMap[it]) }))
	override val size: Int
		get() = baseMap.size
	override val values: MutableSet<V>
		get() = HookAbleMutableSet(inverseMap.keys, MutableSetHock(clear = {baseMap.clear()}, remove = { baseMap.remove(inverseMap[it]) }))

	override fun containsKey(key: K): Boolean = baseMap.contains(key)

	override fun containsValue(value: V): Boolean = inverseMap.contains(value)

	override fun get(key: K): V? = baseMap.get(key)

	override fun isEmpty(): Boolean = baseMap.isEmpty()

	override fun clear(){
		baseMap.clear()
		inverseMap.clear()
	}

	override fun put(key: K, value: V): V? {
		require(!containsValue(value)) { "$value is contains in this map" }
		inverseMap[value] = key
		return baseMap.put(key, value)
	}

	override fun putAll(from: Map<out K, V>) = from.forEach{ (key, value) -> put(key, value) }

	override fun remove(key: K): V?{
		inverseMap[baseMap[key]]
		return baseMap.remove(key)
	}

	override fun forcePut(key: K, value: V): V? {
		return baseMap.put(key, value).also { inverseMap[value] = key }
	}

	override fun inverse(): MutableBiMap<V, K> = object : MutableBiMap<V, K>{
		override val entries: MutableSet<MutableMap.MutableEntry<V, K>>
			get() = MappedResultMutableSet(inverseMap.entries) { base ->
				object : MutableMap.MutableEntry<V, K> {
					override val key: V
						get() = base.key
					override val value: K
						get() = base.value

					override fun setValue(newValue: K): K {
						require(!containsValue(newValue)) { "$value is contains in this map" }
						return base.setValue(newValue)
					}
				}
			}
		override val keys: MutableSet<V>
			get() = HookAbleMutableSet(inverseMap.keys, MutableSetHock(clear = {baseMap.clear()}, remove = { baseMap.remove(inverseMap[it]) }))
		override val size: Int
			get() = inverseMap.size
		override val values: MutableSet<K>
			get() = HookAbleMutableSet(baseMap.keys, MutableSetHock(clear = {inverseMap.clear()}, remove = { inverseMap.remove(baseMap[it]) }))

		override fun containsKey(key: V): Boolean = inverseMap.contains(key)

		override fun containsValue(value: K): Boolean = baseMap.contains(value)

		override fun get(key: V): K? = inverseMap.get(key)

		override fun isEmpty(): Boolean = inverseMap.isEmpty()

		override fun clear(){
			inverseMap.clear()
			baseMap.clear()
		}

		override fun put(key: V, value: K): K? {
			require(!containsValue(value)) { "$value is contains in this map" }
			baseMap[value] = key
			return inverseMap.put(key, value)
		}

		override fun putAll(from: Map<out V, K>) = from.forEach{ (key, value) -> put(key, value) }

		override fun remove(key: V): K?{
			baseMap[inverseMap[key]]
			return inverseMap.remove(key)
		}

		override fun forcePut(key: V, value: K): K? {
			return inverseMap.put(key, value).also { baseMap[value] = key }
		}

		override fun inverse(): MutableBiMap<K, V> = this@MutableHashBiMap
	}

}
