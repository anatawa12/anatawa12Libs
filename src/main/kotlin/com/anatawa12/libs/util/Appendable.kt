package com.anatawa12.libs.util

/**
 * Created by anatawa12 on 2018/04/20.
 */
/**
 * append [string]
 */
operator fun Appendable.plusAssign(string: String) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: String) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Any?) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Boolean) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Char) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Int) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Short) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Long) {
	append(string)
}

/**
 * append [string]
 */
operator fun StringBuilder.plusAssign(string: Byte) {
	append(string)
}