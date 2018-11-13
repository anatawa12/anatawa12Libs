package com.anatawa12.libs.io

import java.lang.Character.reverseBytes
import java.lang.Integer.reverseBytes
import java.lang.Long.reverseBytes
import java.lang.Short.reverseBytes

/**
 * Created by anatawa12 on 2018/11/06.
 */
enum class Endian {
	BigEndian {
		override fun fromBigEndian(v: Char): Char = v
		override fun fromBigEndian(v: Short): Short = v
		override fun fromBigEndian(v: Int): Int = v
		override fun fromBigEndian(v: Long): Long = v

		override fun toBigEndian(v: Char): Char = v
		override fun toBigEndian(v: Short): Short = v
		override fun toBigEndian(v: Int): Int = v
		override fun toBigEndian(v: Long): Long = v
	},
	LittleEndian {
		override fun fromBigEndian(v: Char): Char = reverseBytes(v)
		override fun fromBigEndian(v: Short): Short = reverseBytes(v)
		override fun fromBigEndian(v: Int): Int = reverseBytes(v)
		override fun fromBigEndian(v: Long): Long = reverseBytes(v)

		override fun toBigEndian(v: Char): Char = reverseBytes(v)
		override fun toBigEndian(v: Short): Short = reverseBytes(v)
		override fun toBigEndian(v: Int): Int = reverseBytes(v)
		override fun toBigEndian(v: Long): Long = reverseBytes(v)
	}
	;
	abstract fun fromBigEndian(v: Char): Char
	abstract fun fromBigEndian(v: Short): Short
	abstract fun fromBigEndian(v: Int): Int
	abstract fun fromBigEndian(v: Long): Long

	abstract fun toBigEndian(v: Char): Char
	abstract fun toBigEndian(v: Short): Short
	abstract fun toBigEndian(v: Int): Int
	abstract fun toBigEndian(v: Long): Long
}
