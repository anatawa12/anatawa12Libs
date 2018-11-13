package com.anatawa12.libs.delegation

import java.lang.reflect.Field
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by anatawa12 on 2018/04/07.
 */

/**
 * this make reflection useful.
 * ```kotlin
 * val xxx by reflectionDelegation(XXX::class.java.getField("xxx"))
 * ```
 * @param property1 target field
 */
@Suppress("UNCHECKED_CAST")
fun <T, R> reflectionDelegation(property1: Field) = object : ReadWriteProperty<T, R>{
	init {
		property1.isAccessible = true
	}

	override fun getValue(thisRef: T, property: KProperty<*>): R {
		return property1.get(thisRef) as R
	}

	override fun setValue(thisRef: T, property: KProperty<*>, value: R) {
		property1.set(thisRef, value)
	}
}
