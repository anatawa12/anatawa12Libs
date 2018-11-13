package com.anatawa12.libs.enums

import java.util.*

/**
 * Created by anatawa12 on 2018/07/06.
 */

/**
 * Returns a [EnumSet] containing all elements.
 */
inline fun <reified E: Enum<E>> Iterable<E>.toEnumSet() = enumSetOf<E>().apply { this@toEnumSet.forEach { add(it) } }
