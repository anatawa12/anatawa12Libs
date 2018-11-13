package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2018/02/25.
 */

/**
 * sort with lambda function
 */
fun <T> MutableList<T>.sortWith(comparator: (T, T) -> Int) {
	if (size > 1) java.util.Collections.sort(this, comparator)
}