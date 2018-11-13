package com.anatawa12.libs.util

/**
 * Created by anatawa12 on 2017/11/06.
 */

fun Double.toBytes(): ByteArray = java.lang.Double.doubleToLongBits(this).toBytes()

fun ByteArray.toDouble(): Double {
	if (this.size != 8) {
		throw IllegalArgumentException("bytes's length isn't 8")
	}
	val l = this.toLong()
	return java.lang.Double.longBitsToDouble(l)
}

fun Float.toBytes(): ByteArray = java.lang.Float.floatToIntBits(this).toBytes()

fun ByteArray.toFloat(): Float = java.lang.Float.intBitsToFloat(this.toInt())

fun ByteArray.toInt(): Int {
	if (this.size != 4) {
		throw IllegalArgumentException("bytes's length isn't 4")
	}
	return (this[3].toInt() shl (8*3))
			.or (this[2].toInt() shl (8*2))
			.or (this[1].toInt() shl (8*1))
			.or (this[0].toInt() shl (8*0))
}

fun Int.toBytes(): ByteArray {
	return byteArrayOf((this shr (8*3)).toByte(),
			(this shr (8*2)).toByte(),
			(this shr (8*1)).toByte(),
			(this shr (8*0)).toByte())
}

fun ByteArray.toLong(): Long {
	if (this.size != 4) {
		throw IllegalArgumentException("bytes's length isn't 4")
	}
	return (this[7].toLong() shl (8*7))
			.or(this[6].toLong() shl (8*6))
			.or (this[5].toLong() shl (8*5))
			.or (this[4].toLong() shl (8*4))
			.or (this[3].toLong() shl (8*3))
			.or (this[2].toLong() shl (8*2))
			.or (this[1].toLong() shl (8*1))
			.or (this[0].toLong() shl (8*0))
}

fun Long.toBytes(): ByteArray = byteArrayOf(
		(this shr (8*7)).toByte(),
		(this shr (8*6)).toByte(),
		(this shr (8*5)).toByte(),
		(this shr (8*4)).toByte(),
		(this shr (8*3)).toByte(),
		(this shr (8*2)).toByte(),
		(this shr (8*1)).toByte(),
		(this shr (8*0)).toByte())