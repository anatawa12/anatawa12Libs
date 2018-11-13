package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2017/10/30.
 */
/**
 * deep clone of list
 */
fun <E: ICloneable<E>> List<E>.cloneList(): List<E> {
	return this.map { it.clone() }
}

