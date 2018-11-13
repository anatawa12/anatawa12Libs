package com.anatawa12.libs.enums

import java.util.*

/**
 * Created by anatawa12 on 2017/12/15.
 */

/**
 * Returns a new EnumSet. The returned list is serializable.
 */
inline fun <reified E : Enum<E>> enumSetOf(): EnumSet<E> = EnumSet.noneOf(E::class.java)

/**
 * Returns a new EnumSet of given elements. The returned list is serializable.
 */
inline fun <reified E : Enum<E>> enumSetOf(vararg arg: E): EnumSet<E> = enumSetOf<E>().apply { arg.forEach { add(it) } }