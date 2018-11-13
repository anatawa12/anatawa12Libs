package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2017/09/24.
 */

/**
 * cast to not nulls array. this is not unsafe
 */
fun <T> Array<T?>.toNonNullable(): Array<T> {
	if (any { it == null }) throw TypeCastException("can't cast to non nullable array")
	@Suppress("UNCHECKED_CAST")
	return this as Array<T>
}