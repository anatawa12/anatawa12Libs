package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/03/14.
 */
/**
 * for-each with remove() function
 */
fun <T> MutableIterable<T>.forEachWithRemove(block: Remover.(T)->Unit) {
	val iterator = iterator()
	val remover = RemoverImpl(iterator)
	while (iterator.hasNext()) {
		remover.block(iterator.next())
	}
}

private class RemoverImpl(val iterator: MutableIterator<*>) : Remover{
	override fun remove() = iterator.remove()
}

/**
 * for-each's remove() function
 */
interface Remover {
	fun remove()
}

/**
 * for-each with remove(), add([T]), set(T) function and goNext or go previous
 */
fun <T> MutableList<T>.forEachWithOperator(index: Int = 0, block: ListOperator<T>.(T)->NextOp) {
	var nextOp = NextOp.Next
	var iterator = listIterator(index)
	var op = ListOperatorImpl(iterator)
	while (nextOp.has(iterator)) {
		nextOp = op.block(nextOp.get(iterator))
	}
}


/**
 * for-each's remove(), add([T]), set(T) function
 */
interface ListOperator<E> : Remover {
	fun add(e: E)
	fun set(e: E)
}

private class ListOperatorImpl<E>(private val iterator: MutableListIterator<E>): ListOperator<E> {
	override fun remove() {
		iterator.remove()
	}

	override fun add(e: E) {
		iterator.add(e)
	}

	override fun set(e: E) {
		iterator.set(e)
	}

}

enum class NextOp {
	Next,
	Previous

	;
	fun has(i: ListIterator<*>) = when(this) {
		Next -> i.hasNext()
		Previous -> i.hasPrevious()
	}

	fun <T>  get(i: ListIterator<T>) = when(this) {
		Next -> i.next()
		Previous -> i.previous()
	}
}