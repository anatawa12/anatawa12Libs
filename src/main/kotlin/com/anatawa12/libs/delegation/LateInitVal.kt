package com.anatawa12.libs.delegation

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by anatawa12 on 2018/04/21.
 */
/**
 * late init value that can set once.
 */
private class LateInitVal<T: Any> : ReadWriteProperty<Any?, T> {
	lateinit var value: T

	override fun getValue(thisRef: Any?, property: KProperty<*>): T = value

	override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
		if (this::value.isInitialized) error("this property is already initialized")
		this.value = value
	}
}

fun <T : Any> lateInitVal(): ReadWriteProperty<Any?, T> = LateInitVal()
