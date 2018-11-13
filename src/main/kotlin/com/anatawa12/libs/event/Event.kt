package com.anatawa12.libs.event

/**
 * Created by anatawa12 on 2018/07/16.
 */
/**
 * @param E event's Lambda type
 */
interface IEvent<E> {
	operator fun plusAssign(event: E)
	fun register(event: E): IEventRemover
	val size: Int
}

abstract class EventBase<E> : IEvent<E> {
	protected val functions = mutableSetOf<E>()

	override operator fun plusAssign(event: E) {
		functions.add(event)
	}

	override fun register(event: E): IEventRemover {
		functions.add(event)
		return object : IEventRemover { override fun remove() { functions.remove(event) } }
	}

	override val size get() = functions.size
}

class Event0(): EventBase<()->Unit>() {
	constructor(vararg events: ()->Unit): this() {
		functions.addAll(events)
	}

	operator fun invoke() {
		functions.forEach {
			try {
				it()
			} catch (throwable: Throwable) {
			}
		}
	}
}

class Event1<T>(): EventBase<(T)->Unit>() {
	constructor(vararg events: (T) -> Unit) : this() {
		functions.addAll(events)
	}

	operator fun invoke(arg: T) {
		functions.forEach {
			try {
				it(arg)
			} catch (throwable: Throwable) {
			}
		}
	}
}
