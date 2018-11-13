package com.anatawa12.libs.io

import com.anatawa12.libs.unsigned.toUInt
import java.io.*
import java.lang.Double.longBitsToDouble
import java.lang.Float.intBitsToFloat

/**
 * Created by anatawa12 on 2018/11/06.
 */

/**
 * DataInputStream with endian
 */
class EndianedDataInputStream private constructor(stream: InputStream) : InputStream(), DataInput, Closeable {
	private val inputStream: DataInputStream
	private val base = PushbackInputStream(stream)

	init {
		inputStream = DataInputStream(base)
	}

	var endian: Endian = Endian.BigEndian

	override fun read(): Int {
		return inputStream.read()
	}

	override fun readBoolean(): Boolean {
		return inputStream.readBoolean()
	}

	override fun readByte(): Byte {
		return inputStream.readByte()
	}

	override fun readShort(): Short {
		return endian.fromBigEndian(inputStream.readShort())
	}

	override fun readInt(): Int {
		return endian.fromBigEndian(inputStream.readInt())
	}

	override fun readLong(): Long {
		return endian.fromBigEndian(inputStream.readLong())
	}

	override fun readFloat(): Float {
		return intBitsToFloat(endian.fromBigEndian(inputStream.readInt()))
	}

	override fun readDouble(): Double {
		return longBitsToDouble(endian.fromBigEndian(inputStream.readLong()))
	}

	override fun readChar(): Char {
		return endian.fromBigEndian(inputStream.readChar())
	}

	// bytes

	override fun readUTF(): String {
		return inputStream.readUTF()
	}

	private var lineBuffer: CharArray? = null

	override fun readLine(): String? {
		var buf: CharArray = lineBuffer ?: run {lineBuffer = CharArray(128);lineBuffer!!}

		var room = buf.size
		var offset = 0
		var c: Int

		loop@ while (true) {
			c = inputStream.read()
			when (c) {
				-1, '\n'.toInt() -> break@loop

				'\r'.toInt() -> {
					val c2 = inputStream.read()
					if (c2 != '\n'.toInt() && c2 != -1) {
						base.unread(c2)
					}
					break@loop
				}

				else -> {
					if (--room < 0) {
						buf = CharArray(offset + 128)
						room = buf.size - offset - 1
						System.arraycopy(lineBuffer, 0, buf, 0, offset)
						lineBuffer = buf
					}
					buf[offset++] = c.toChar()
				}
			}
		}
		return if (c == -1 && offset == 0) {
			null
		} else String(buf, 0, offset)
	}

	override fun skipBytes(n: Int): Int {
		return inputStream.skipBytes(n)
	}

	override fun readFully(b: ByteArray) {
		return inputStream.readFully(b)
	}

	override fun readFully(b: ByteArray, off: Int, len: Int) {
		return inputStream.readFully(b, off, len)
	}

	override fun readUnsignedShort(): Int {
		return readShort().toUInt()
	}

	override fun readUnsignedByte(): Int {
		return readByte().toUInt()
	}
}