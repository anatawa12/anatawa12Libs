package com.anatawa12.libs.collections

/**
 * Created by anatawa12 on 2017/10/30.
 */

/**
 * this make we can clone without reflection
 */
interface ICloneable<E : Any> : Cloneable {
	public override fun clone(): E
}
