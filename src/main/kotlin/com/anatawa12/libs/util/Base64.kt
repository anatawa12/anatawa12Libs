@file:JvmName("Base64")
package com.anatawa12.libs.util

import java.io.ByteArrayOutputStream

/**
 * Created by anatawa12 on 2017/09/23.
 */

/**
 * [ByteArray] to [String] by base64
 */
fun encodeToBase64(src: ByteArray): String {
    return src.toBase64()
}

/**
 * [String] to [ByteArray] by base64
 */
fun decodeFromBase64(src: String): ByteArray {
    return src.fromBase64()
}

private val byte2Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

/**
 * [ByteArray] to [String] by base64
 */
fun ByteArray.toBase64(maxLineLength: Int? = null, newLine: String = "\r\n", padding: Char? = null, c62: Char = '+', c63: Char = '/'): String {
    val sb = StringBuilder(size / 3 * 4)
    var line = 0
    fun add(char: Char) {
        if (maxLineLength == line) {
            line = 0
            sb.append(newLine)
        }
        when (char) {
            '+' -> sb.append(c62)
            '/' -> sb.append(c63)
            else -> sb.append(char)
        }
        line++
    }
    for (i in 0..size - 3 step 3) {
        val a = this[i + 0].toInt() and 0xFF
        val b = this[i + 1].toInt() and 0xFF
        val c = this[i + 2].toInt() and 0xFF
        add(byte2Char[a ushr 2])
        add(byte2Char[(a and 0x03 shl 4) or (b ushr 4)])
        add(byte2Char[(b and 0x0F shl 2) or (c ushr 6)])
        add(byte2Char[(c and 0x3F)])
    }
    var read = lastIndex / 3 * 3
    if (read == size) return sb.toString()

    val a = this[read++].toInt() and 0xFF
    add(byte2Char[a ushr 2])
    if (read == size) {
        add(byte2Char[a and 0x03 shl 4])
        if (padding != null) {
            sb.append(padding)
            sb.append(padding)
        }
        return sb.toString()
    }

    val b = this[read++].toInt() and 0xFF
    add(byte2Char[(a and 0x03 shl 6) or (b ushr 4)])
    if (padding != null) sb.append(padding)
    add(byte2Char[b and 0x0F shl 2])
    return sb.toString()
    //return DatatypeConverter.printBase64Binary(this)
}

private fun char2Byte(char: Char): Int {
    val index = byte2Char.indexOf(char)
    if (index == -1) require(false) { "$char is not valid char" }
    return index
}

/**
 * [String] to [ByteArray] by base64
 */
fun String.fromBase64(padding: Char? = '=', newLine: String? = null, c62: Char = '+', c63: Char = '/'): ByteArray
        = (newLine?.let { replace(it, "") } ?: this).run {
    fun char2Byte(char: Char): Int {
        if (char == c62) return 62
        if (char == c63) return 63
        val index = byte2Char.indexOf(char)
        if (index == -1) require(false) { "$char is not valid char" }
        return index
    }

    val bos = ByteArrayOutputStream((length * (1 / 4 * 3.0)).toInt())

    for (i in 0..length - 4 step 4) {
        val a = char2Byte(this[i + 0])
        val b = char2Byte(this[i + 1])
        bos.write((a shl 2) or (b ushr 4))
        if (this[i + 2] == padding) return bos.toByteArray()
        val c = char2Byte(this[i + 2])
        bos.write((b and 0xF shl 4) or (c ushr 2))

        if (this[i + 3] == padding) return bos.toByteArray()
        val d = char2Byte(this[i + 3])
        bos.write((c and 0x3 shl 6) or d)
    }
    val i = lastIndex / 4 * 4

    if ((i + 0) == length) return bos.toByteArray()
    if ((i + 0) == lastIndex) return bos.toByteArray()
    val a = char2Byte(this[i + 0])
    val b = char2Byte(this[i + 1])
    bos.write((a shl 2) or (b ushr 4))
    if ((i + 1) == lastIndex) return bos.toByteArray()
    if (this[i + 2] == padding) return bos.toByteArray()
    val c = char2Byte(this[i + 2])
    bos.write((b and 0xF shl 4) or (c ushr 2))
    if ((i + 2) == lastIndex) return bos.toByteArray()

    if (this[i + 3] == padding) return bos.toByteArray()
    val d = char2Byte(this[i + 3])
    bos.write((c and 0x3 shl 6) or d)

    return bos.toByteArray()
    //return DatatypeConverter.parseBase64Binary(this)
}
