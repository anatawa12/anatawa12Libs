package com.anatawa12.libs.io

import java.io.Closeable
import java.io.DataOutput
import java.io.DataOutputStream
import java.io.OutputStream
import java.lang.Double.doubleToLongBits
import java.lang.Float.floatToIntBits

/**
 * Created by anatawa12 on 2018/11/06.
 */

/**
 * DataOutputStream with endian
 */
class EndianedDataOutputStream private constructor(private val baseDataInput: DataOutputStream) : OutputStream(), DataOutput, Closeable {
	constructor(stream: OutputStream): this(DataOutputStream(stream))

	var endian: Endian = Endian.BigEndian

	override fun write(b: Int) {
		baseDataInput.write(b)
	}

	override fun writeBoolean(v: Boolean) {
		baseDataInput.writeBoolean(v)
	}

	override fun writeByte(v: Int) {
		baseDataInput.writeByte(v)
	}

	override fun writeShort(v: Int) {
		baseDataInput.writeShort(endian.fromBigEndian(v.toShort()).toInt())
	}

	override fun writeInt(v: Int) {
		baseDataInput.writeInt(v)
	}

	override fun writeLong(v: Long) {
		baseDataInput.writeLong(endian.fromBigEndian(v))
	}

	override fun writeFloat(v: Float) {
		baseDataInput.writeInt(endian.fromBigEndian(floatToIntBits(v)))
	}

	override fun writeDouble(v: Double) {
		baseDataInput.writeLong(endian.fromBigEndian(doubleToLongBits(v)))
	}

	override fun writeChar(v: Int) {
		baseDataInput.writeChar(endian.fromBigEndian(v))
	}

	// bytes

	override fun writeBytes(s: String) {
		baseDataInput.writeBytes(s)
	}

	override fun writeChars(s: String) {
		val len = s.length
		for (i in 0 until len) {
			writeChar(s[i].toInt())
		}
	}

	override fun writeUTF(s: String?) {
		baseDataInput.writeUTF(s)
	}

	override fun close() {
		baseDataInput.close()
	}
}