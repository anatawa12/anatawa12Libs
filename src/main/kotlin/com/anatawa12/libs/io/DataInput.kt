@file:Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE")

package com.anatawa12.libs.io

import java.io.DataInput

/**
 * Created by anatawa12 on 2018/02/25.
 */
inline fun DataInput.readULong() : ULong = readLong().toULong()
inline fun DataInput.readUInt() : UInt = readInt().toUInt()
inline fun DataInput.readUShort() : UShort = readShort().toUShort()
inline fun DataInput.readUByte() : UByte = readByte().toUByte()

inline fun DataInput.readBytes(size: Int): ByteArray {
	val result = ByteArray(size)
	for (index in result.indices){
		result[index] = readByte()
	}
	return result
}