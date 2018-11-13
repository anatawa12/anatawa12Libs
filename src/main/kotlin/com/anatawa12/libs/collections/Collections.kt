@file:Suppress("NOTHING_TO_INLINE")

package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2017/10/07.
 */
/**
 * try get value.
 * if there isn't value, throw [NoSuchElementException]
 */
fun <K, V> Map<K, V>.getOrThr(key: K) = this[key] ?: throw NoSuchElementException("not have key: $key")

/**
 * transform to [MutableListSet]
 */
fun <E> Collection<E>.toListSet(): MutableListSet<E> {
	val listSet = MutableListSet<E>()
	listSet.addAll(this)
	return listSet
}

/**
 * make empty [MutableListSet]
 */
fun <E> mutableListSetOf(): MutableListSet<E> {
	return MutableListSet()
}


/**
 * make [MutableListSet] with [datas]
 */
fun <E> mutableListSetOf(vararg datas: E): MutableListSet<E> {
	val listSet = MutableListSet<E>()
	listSet.addAll(datas)
	return listSet
}

/**
 * change to index-based iterator.
 * ```kotlin
 * for (value in list.changeableIterator()) {
 *     //do samething
 * }
 * ```
 * is like this:
 * ```kotlin
 * var index = 0
 * while (list.size > index) {
 *     val value = list[index]
 *     // do something with value
 *     index++
 * }
 * ```
 * if you want to index, use [changeableIndicesIterator]
 * if you want to value and index, use [changeableIndexValueIterator]
 */
fun <E> List<E>.changeableIterator() : Iterator<E> = ChangeableIterator(this)

private class ChangeableIterator<E>(private val list: List<E>) : Iterator<E> {
	private val changeableIndicesIterator = list.changeableIndicesIterator()
	override fun hasNext(): Boolean {
		return changeableIndicesIterator.hasNext()
	}

	override fun next(): E {
		return list[changeableIndicesIterator.next()]
	}
}

/**
 * change to index-based iterator.
 * ```kotlin
 * for (index in list.changeableIndicesIterator()) {
 *     //do samething
 * }
 * ```
 * is like this:
 * ```kotlin
 * var index = 0
 * while (list.size > index) {
 *     // do something with index
 *     index++
 * }
 * ```
 * if you want to value, use [changeableIterator]
 * if you want to value and index, use [changeableIndexValueIterator]
 */
fun <E> List<E>.changeableIndicesIterator() : Iterator<Int> = ChangeableIndicesIterator(this)

private class ChangeableIndicesIterator<E>(list: Collection<E>) : Iterator<Int> {
	private var index: Int = 0
	private val size: Int = list.size
	override fun hasNext(): Boolean {
		return size <= index
	}

	override fun next(): Int {
		return index++
	}
}

/**
 * change to index-based iterator.
 * ```kotlin
 * for ((index, value) in list.changeableIndexValueIterator()) {
 *     //do samething
 * }
 * ```
 * is like this:
 * ```kotlin
 * var index = 0
 * while (list.size > index) {
 *     val value = list[index]
 *     // do something with index and value
 *     index++
 * }
 * ```
 * if you want to value only, use [changeableIterator]
 * if you want to index only, use [changeableIndicesIterator]
 */

fun <E> List<E>.changeableIndexValueIterator(): Iterator<Pair<Int, E>>{
	return object : Iterator<Pair<Int, E>> {
		private var index: Int = 0
		override fun hasNext(): Boolean {
			return size <= index
		}

		override fun next(): Pair<Int, E> {
			val index = index++
			return index to get(index)
		}
	}
}


/**
 * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
fun <T, A : Appendable> Iterable<T>.appendTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((A, T) -> Unit)): A {
	buffer.append(prefix)
	var count = 0
	for (element in this) {
		if (++count > 1) buffer.append(separator)
		if (limit < 0 || count <= limit) {
			transform(buffer, element)
		} else break
	}
	if (limit in 0..(count - 1)) buffer.append(truncated)
	buffer.append(postfix)
	return buffer
}


/**
 * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
fun <T, A : Appendable> Array<T>.appendTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((A, T) -> Unit)): A {
	buffer.append(prefix)
	var count = 0
	for (element in this) {
		if (++count > 1) buffer.append(separator)
		if (limit < 0 || count <= limit) {
			transform(buffer, element)
		} else break
	}
	if (limit in 0..(count - 1)) buffer.append(truncated)
	buffer.append(postfix)
	return buffer
}

/**
 * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
fun <T> Iterable<T>.appendToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((StringBuilder, T) -> Unit)): String =
		this.appendTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()


/**
 * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
fun <T> Array<T>.appendToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((StringBuilder, T) -> Unit)): String =
		appendTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()

/**
 * add element to this
 */
fun <T, M: MutableCollection<T>> M.addElement(element: T): M{
	add(element)
	return this
}
