package com.anatawa12.libs.vector

/**
 * Created by anatawa12 on 2017/11/08.
 */

/**
 * 2 dimensional vector of double
 */
data class Vec2d constructor(val x: Double, val y: Double){
	fun toVec2d(): Vec2d = this
	fun toVec2f(): Vec2f = Vec2f(x.toFloat(), y.toFloat())
	fun toVec2l(): Vec2l = Vec2l(x.toLong(), y.toLong())
	fun toVec2i(): Vec2i = Vec2i(x.toInt(), y.toInt())

	operator fun plus(vec: Vec2d) = Vec2d(x + vec.x, y + vec.y)
	operator fun minus(vec: Vec2d) = Vec2d(x - vec.x, y - vec.y)
	operator fun times(other: Double) = Vec2d(x * other, y * other)
	operator fun div(other: Double) = Vec2d(x / other, y / other)
}

/**
 * 2 dimensional vector of float
 */
data class Vec2f constructor(val x: Float, val y: Float){
	fun toVec2d(): Vec2d = Vec2d(x.toDouble(), y.toDouble())
	fun toVec2f(): Vec2f = this
	fun toVec2l(): Vec2l = Vec2l(x.toLong(), y.toLong())
	fun toVec2i(): Vec2i = Vec2i(x.toInt(), y.toInt())

	operator fun plus(vec: Vec2f) = Vec2f(x + vec.x, y + vec.y)
	operator fun minus(vec: Vec2f) = Vec2f(x - vec.x, y - vec.y)
	operator fun times(other: Float) = Vec2f(x * other, y * other)
	operator fun div(other: Float) = Vec2f(x / other, y / other)
}

/**
 * 2 dimensional vector of long
 */
data class Vec2l constructor(val x: Long, val y: Long){
	fun toVec2d(): Vec2d = Vec2d(x.toDouble(), y.toDouble())
	fun toVec2f(): Vec2f = Vec2f(x.toFloat(), y.toFloat())
	fun toVec2l(): Vec2l = this
	fun toVec2i(): Vec2i = Vec2i(x.toInt(), y.toInt())

	operator fun plus(vec: Vec2l) = Vec2l(x + vec.x, y + vec.y)
	operator fun minus(vec: Vec2l) = Vec2l(x - vec.x, y - vec.y)
	operator fun times(other: Long) = Vec2l(x * other, y * other)
	operator fun div(other: Long) = Vec2l(x / other, y / other)
}

/**
 * 2 dimensional vector of int
 */
data class Vec2i constructor(val x: Int, val y: Int){
	fun toVec2d(): Vec2d = Vec2d(x.toDouble(), y.toDouble())
	fun toVec2f(): Vec2f = Vec2f(x.toFloat(), y.toFloat())
	fun toVec2l(): Vec2l = Vec2l(x.toLong(), y.toLong())
	fun toVec2i(): Vec2i = this

	operator fun plus(vec: Vec2i) = Vec2i(x + vec.x, y + vec.y)
	operator fun minus(vec: Vec2i) = Vec2i(x - vec.x, y - vec.y)
	operator fun times(other: Int) = Vec2i(x * other, y * other)
	operator fun div(other: Int) = Vec2i(x / other, y / other)
}

@Deprecated("", replaceWith = ReplaceWith("Vec2d(x, y)", "com.anatawa12.libs.vector.Vec2d"))
fun vec2Of(x: Double, y: Double): Vec2d = Vec2d(x, y)

@Deprecated("", replaceWith = ReplaceWith("Vec2f(x, y)", "com.anatawa12.libs.vector.Vec2f"))
fun vec2Of(x: Float, y: Float): Vec2f = Vec2f(x, y)

@Deprecated("", replaceWith = ReplaceWith("Vec2l(x, y)", "com.anatawa12.libs.vector.Vec2l"))
fun vec2Of(x: Long, y: Long): Vec2l = Vec2l(x, y)

@Deprecated("", replaceWith = ReplaceWith("Vec2i(x, y)", "com.anatawa12.libs.vector.Vec2i"))
fun vec2Of(x: Int, y: Int): Vec2i = Vec2i(x, y)
