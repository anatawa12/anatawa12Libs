@file:JvmName("UnionLib")
package com.anatawa12.libs.union

/**
 * Created by anatawa12 on 2018/01/06.
 */

sealed class Union<out L, out R> {
	open fun getL(): L = throw IllegalStateException()
	open fun isL(): Boolean = false
	open fun getR(): R = throw IllegalStateException()
	open fun isR(): Boolean = false
}

private class L<out Lt>(private val value: Lt) : Union<Lt, Nothing>() {
	override fun getL(): Lt = value
	override fun isL(): Boolean = true
}

private class R<out Rt>(private val value: Rt) : Union<Nothing, Rt>() {
	override fun getR(): Rt = value
	override fun isR(): Boolean = true
}

inline fun <L, R, Result> Union<L, R>.runLR(l: (L) -> Result, r: (R) -> Result): Result = if (isL()) {
	l(getL())
} else {
	r(getR())
}

fun <T> T.l(): Union<T, Nothing> = L(this)

fun <T> T.r(): Union<Nothing, T> = R(this)