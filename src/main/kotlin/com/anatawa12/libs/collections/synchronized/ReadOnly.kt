package com.anatawa12.libs.collections.synchronized

import com.anatawa12.libs.collections.BiMap
import com.anatawa12.libs.collections.NotNullBiMap
import com.anatawa12.libs.collections.NotNullMap

/**
 * Created by anatawa12 on 2018/11/10.
 */

/**
 * Returns a synchronized (thread-safe) Set backed by the specified Set.
 * In order to guarantee serial access, it is critical that all access to the backing Set is accomplished through the returned Set.
 */
fun <T> Set<T>.synchronized(): Set<T> = SynchronizedSet(this)

/**
 * Returns a synchronized (thread-safe) NotNullMap backed by the specified NotNullMap.
 * In order to guarantee serial access, it is critical that all access to the backing NotNullMap is accomplished through the returned NotNullMap.
 */
fun <K, V: Any> NotNullMap<K, V>.synchronized(): NotNullMap<K, V> = SynchronizedNotNullMap(this)

/**
 * Returns a synchronized (thread-safe) NotNullBiMap backed by the specified NotNullBiMap.
 * In order to guarantee serial access, it is critical that all access to the backing NotNullBiMap is accomplished through the returned NotNullBiMap.
 */
fun <K: Any, V: Any> NotNullBiMap<K, V>.synchronized(): NotNullBiMap<K, V> = SynchronizedNotNullBiMap(this)

/**
 * Returns a synchronized (thread-safe) Map backed by the specified Map.
 * In order to guarantee serial access, it is critical that all access to the backing Map is accomplished through the returned Map.
 */
fun <K, V>Map<K, V>.synchronized(): Map<K, V> = SynchronizedMap(this)

/**
 * Returns a synchronized (thread-safe) List backed by the specified List.
 * In order to guarantee serial access, it is critical that all access to the backing List is accomplished through the returned List.
 */
fun <T> List<T>.synchronized(): List<T> = SynchronizedList(this)

/**
 * Returns a synchronized (thread-safe) Collection backed by the specified Collection.
 * In order to guarantee serial access, it is critical that all access to the backing Collection is accomplished through the returned Collection.
 */
fun <T> Collection<T>.synchronized(): Collection<T> = SynchronizedCollection(this)

/**
 * Returns a synchronized (thread-safe) BiMap backed by the specified BiMap.
 * In order to guarantee serial access, it is critical that all access to the backing BiMap is accomplished through the returned BiMap.
 */
fun <K, V> BiMap<K, V>.synchronized(): BiMap<K, V> = SynchronizedBiMap(this)

// classes

internal class SynchronizedSet<T>(override val base: Set<T>) : SynchronizedCollection<T>(base), Set<T>

internal open class SynchronizedNotNullMap<K, V: Any>(override val base: NotNullMap<K, V>): SynchronizedMap<K, V>(base), NotNullMap<K, V> {
	override fun get(key: K) = synchronized(lock) { base.get(key) }
}

internal open class SynchronizedNotNullBiMap<K: Any, V: Any>(override val base: NotNullBiMap<K, V>) : SynchronizedNotNullMap<K, V>(base), NotNullBiMap<K, V> {
	override fun inverse() = synchronized(lock) { base.inverse() }

	override val values get() = synchronized(lock) { base.values }
}

internal open class SynchronizedMap<K, V>(protected open val base: Map<K, V>) : Map<K, V> {
	protected val lock: Any = this

	override val entries get() = synchronized(lock) { base.entries }
	override val keys get() = synchronized(lock) { base.keys }
	override val size get() = synchronized(lock) { base.size }
	override val values get() = synchronized(lock) { base.values }

	override fun containsKey(key: K) = synchronized(lock) { base.containsKey(key) }

	override fun containsValue(value: V) = synchronized(lock) { base.containsValue(value) }

	override fun get(key: K) = synchronized(lock) { base.get(key) }

	override fun isEmpty() = synchronized(lock) { base.isEmpty() }
}

internal class SynchronizedList<T>(override val base: List<T>) : SynchronizedCollection<T>(base), List<T> {
	override fun get(index: Int) = synchronized(lock) { base.get(index) }

	override fun indexOf(element: T) = synchronized(lock) { base.indexOf(element) }

	override fun lastIndexOf(element: T) = synchronized(lock) { base.lastIndexOf(element) }

	override fun listIterator() = base.listIterator()

	override fun listIterator(index: Int) = base.listIterator(index)

	override fun subList(fromIndex: Int, toIndex: Int) = synchronized(lock) { base.subList(fromIndex, toIndex) }
}

internal open class SynchronizedCollection<T>(protected open val base: Collection<T>) : Collection<T> {
	protected val lock: Any = this

	override val size: Int
		get() = synchronized(lock) { base.size }

	override fun contains(element: T): Boolean = synchronized(lock) { base.contains(element) }

	override fun containsAll(elements: Collection<T>): Boolean = synchronized(lock) { base.containsAll(elements) }

	override fun isEmpty(): Boolean = synchronized(lock) { base.isEmpty() }

	override fun iterator(): Iterator<T> = base.iterator()

	override fun hashCode(): Int = synchronized(lock) { base.hashCode() }

	override fun equals(other: Any?): Boolean  = synchronized(lock) { base.equals(other) }

	override fun toString(): String = synchronized(lock) { base.toString() }
}

internal open class SynchronizedBiMap<K, V>(override val base: BiMap<K, V>): SynchronizedMap<K, V>(base), BiMap<K, V> {
	override fun inverse() = synchronized(lock) { base.inverse() }

	override val values get() = synchronized(lock) { base.values }
}

