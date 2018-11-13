@file:Suppress("NOTHING_TO_INLINE")

package com.anatawa12.libs.io

import java.io.DataInput

/**
 * Created by anatawa12 on 2018/02/25.
 */
inline fun DataInput.readUShort() : Int = readUnsignedShort()
inline fun DataInput.readUByte() : Int = readUnsignedByte()
inline fun DataInput.readBytes(size: Int): ByteArray {
	val result = ByteArray(size)
	for (index in result.indices){
		result[index] = readByte()
	}
	return result
}
