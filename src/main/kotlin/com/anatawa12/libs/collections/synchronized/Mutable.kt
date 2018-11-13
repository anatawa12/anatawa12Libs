package com.anatawa12.libs.collections.synchronized

import com.anatawa12.libs.collections.MutableBiMap
import com.anatawa12.libs.collections.NotNullMutableBiMap
import com.anatawa12.libs.collections.NotNullMutableMap

/**
 * Created by anatawa12 on 2018/11/10.
 */

/**
 * Returns a synchronized (thread-safe) MutableSet backed by the specified MutableSet.
 * In order to guarantee serial access, it is critical that all access to the backing MutableSet is accomplished through the returned MutableSet.
 */
fun <T> MutableSet<T>.synchronized(): MutableSet<T> = SynchronizedMutableSet(this)

/**
 * Returns a synchronized (thread-safe) NotNullMutableMap backed by the specified NotNullMutableMap.
 * In order to guarantee serial access, it is critical that all access to the backing NotNullMutableMap is accomplished through the returned NotNullMutableMap.
 */
fun <K, V: Any> NotNullMutableMap<K, V>.synchronized(): NotNullMutableMap<K, V> = SynchronizedNotNullMutableMap(this)

/**
 * Returns a synchronized (thread-safe) NotNullMutableBiMap backed by the specified NotNullMutableBiMap.
 * In order to guarantee serial access, it is critical that all access to the backing NotNullMutableBiMap is accomplished through the returned NotNullMutableBiMap.
 */
fun <K: Any, V: Any> NotNullMutableBiMap<K, V>.synchronized(): NotNullMutableBiMap<K, V> = SynchronizedNotNullMutableBiMap(this)

/**
 * Returns a synchronized (thread-safe) MutableMap backed by the specified MutableMap.
 * In order to guarantee serial access, it is critical that all access to the backing MutableMap is accomplished through the returned MutableMap.
 */
fun <K, V>MutableMap<K, V>.synchronized(): MutableMap<K, V> = SynchronizedMutableMap(this)

/**
 * Returns a synchronized (thread-safe) MutableList backed by the specified MutableList.
 * In order to guarantee serial access, it is critical that all access to the backing MutableList is accomplished through the returned MutableList.
 */
fun <T> MutableList<T>.synchronized(): MutableList<T> = SynchronizedMutableList(this)

/**
 * Returns a synchronized (thread-safe) MutableCollection backed by the specified MutableCollection.
 * In order to guarantee serial access, it is critical that all access to the backing MutableCollection is accomplished through the returned MutableCollection.
 */
fun <T> MutableCollection<T>.synchronized(): MutableCollection<T> = SynchronizedMutableCollection(this)

/**
 * Returns a synchronized (thread-safe) MutableBiMap backed by the specified MutableBiMap.
 * In order to guarantee serial access, it is critical that all access to the backing MutableBiMap is accomplished through the returned MutableBiMap.
 */
fun <K, V> MutableBiMap<K, V>.synchronized(): MutableBiMap<K, V> = SynchronizedMutableBiMap(this)

// classes

internal open class SynchronizedMutableCollection<T>(override val base: MutableCollection<T>) : SynchronizedCollection<T>(base), MutableCollection<T>{
	override fun add(element: T): Boolean = synchronized(lock) { base.add(element) }

	override fun addAll(elements: Collection<T>): Boolean = synchronized(lock) { base.addAll(elements) }

	override fun clear() = synchronized(lock) { base.clear() }

	override fun iterator(): MutableIterator<T> = base.iterator()

	override fun remove(element: T): Boolean = synchronized(lock) { base.remove(element) }

	override fun removeAll(elements: Collection<T>): Boolean = synchronized(lock) { base.removeAll(elements) }

	override fun retainAll(elements: Collection<T>): Boolean = synchronized(lock) { base.retainAll(elements) }
}

internal class SynchronizedMutableSet<T>(override val base: MutableSet<T>) : SynchronizedMutableCollection<T>(base), MutableSet<T>

internal open class SynchronizedMutableMap<K, V>(override val base: MutableMap<K, V>) : SynchronizedMap<K, V>(base), MutableMap<K, V> {
	override val entries get() = synchronized(lock) { base.entries }
	override val keys get() = synchronized(lock) { base.keys }
	override val size get() = synchronized(lock) { base.size }
	override val values get() = synchronized(lock) { base.values }

	override fun clear() = synchronized(lock) { base.clear() }

	override fun put(key: K, value: V): V? = synchronized(lock) { base.put(key, value) }

	override fun putAll(from: Map<out K, V>) = synchronized(lock) { base.putAll(from) }

	override fun remove(key: K): V? = synchronized(lock) { base.remove(key) }
}

internal open class SynchronizedNotNullMutableMap<K, V: Any>(override val base: NotNullMutableMap<K, V>): SynchronizedMutableMap<K, V>(base), NotNullMutableMap<K, V> {
	override fun get(key: K) = synchronized(lock) { base.get(key) }
}

internal open class SynchronizedNotNullMutableBiMap<K: Any, V: Any>(override val base: NotNullMutableBiMap<K, V>) : SynchronizedNotNullMutableMap<K, V>(base), NotNullMutableBiMap<K, V> {
	override fun forcePut(key: K, value: V) = synchronized(lock) { base.forcePut(key, value) }

	override fun inverse() = synchronized(lock) { base.inverse() }

	override val values get() = synchronized(lock) { base.values }
}

internal class SynchronizedMutableList<T>(override val base: MutableList<T>) : SynchronizedMutableCollection<T>(base), MutableList<T> {
	override fun get(index: Int) = synchronized(lock) { base.get(index) }

	override fun indexOf(element: T) = synchronized(lock) { base.indexOf(element) }

	override fun lastIndexOf(element: T) = synchronized(lock) { base.lastIndexOf(element) }

	override fun listIterator() = base.listIterator()

	override fun listIterator(index: Int) = base.listIterator(index)

	override fun subList(fromIndex: Int, toIndex: Int) = synchronized(lock) { base.subList(fromIndex, toIndex) }

	override fun add(index: Int, element: T) = synchronized(lock) { base.add(index, element) }

	override fun addAll(index: Int, elements: Collection<T>): Boolean = synchronized(lock) { base.addAll(index, elements) }

	override fun removeAt(index: Int): T = synchronized(lock) { base.removeAt(index) }

	override fun set(index: Int, element: T): T = synchronized(lock) { base.set(index, element) }
}

internal open class SynchronizedMutableBiMap<K, V>(override val base: MutableBiMap<K, V>): SynchronizedMutableMap<K, V>(base), MutableBiMap<K, V> {
	override fun inverse() = synchronized(lock) { base.inverse() }

	override val values get() = synchronized(lock) { base.values }

	override fun forcePut(key: K, value: V) = synchronized(lock) { base.forcePut(key, value) }
}

