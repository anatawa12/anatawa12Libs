package com.anatawa12.libs.collections

import kotlin.collections.Collection
import kotlin.collections.Iterable
import kotlin.collections.toList
import kotlin.collections.toBooleanArray as ktToBooleanArray
import kotlin.collections.toByteArray as ktToByteArray
import kotlin.collections.toCharArray as ktToCharArray
import kotlin.collections.toDoubleArray as ktToDoubleArray
import kotlin.collections.toFloatArray as ktToFloatArray
import kotlin.collections.toIntArray as ktToIntArray
import kotlin.collections.toLongArray as ktToLongArray
import kotlin.collections.toShortArray as ktToShortArray

/**
 * Returns an array of Boolean containing all of the elements of this collection.
 */
fun Iterable<Boolean>.toBooleanArray(): BooleanArray = (this as? Collection<Boolean>)?.ktToBooleanArray() ?: toList().ktToBooleanArray()

/**
 * Returns an array of Byte containing all of the elements of this collection.
 */
fun Iterable<Byte>.toByteArray(): ByteArray = (this as? Collection<Byte>)?.ktToByteArray() ?: toList().ktToByteArray()

/**
 * Returns an array of Short containing all of the elements of this collection.
 */
fun Iterable<Short>.toShortArray(): ShortArray = (this as? Collection<Short>)?.ktToShortArray() ?: toList().ktToShortArray()

/**
 * Returns an array of Int containing all of the elements of this collection.
 */
fun Iterable<Int>.toIntArray(): IntArray = (this as? Collection<Int>)?.ktToIntArray() ?: toList().ktToIntArray()

/**
 * Returns an array of Long containing all of the elements of this collection.
 */
fun Iterable<Long>.toLongArray(): LongArray = (this as? Collection<Long>)?.ktToLongArray() ?: toList().ktToLongArray()

/**
 * Returns an array of Float containing all of the elements of this collection.
 */
fun Iterable<Float>.toFloatArray(): FloatArray = (this as? Collection<Float>)?.ktToFloatArray() ?: toList().ktToFloatArray()

/**
 * Returns an array of Double containing all of the elements of this collection.
 */
fun Iterable<Double>.toDoubleArray(): DoubleArray = (this as? Collection<Double>)?.ktToDoubleArray() ?: toList().ktToDoubleArray()

/**
 * Returns an array of Char containing all of the elements of this collection.
 */
fun Iterable<Char>.toCharArray(): CharArray = (this as? Collection<Char>)?.ktToCharArray() ?: toList().ktToCharArray()