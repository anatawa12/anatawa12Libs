package com.anatawa12.libs.unsigned

/**
 * Created by anatawa12 on 2018/02/23.
 */

fun Short.toUInt(): Int = toInt() and 0xFFFF
fun Short.toULong(): Long = toUInt().toLong()

fun Byte.toUInt(): Int = toInt() and 0xFF
fun Byte.toULong(): Long = toUInt().toLong()
