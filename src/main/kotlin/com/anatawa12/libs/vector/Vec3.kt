package com.anatawa12.libs.vector

/**
 * Created by anatawa12 on 2017/11/08.
 */

/**
 * 3 dimensional vector of double
 */
data class Vec3d constructor(val x: Double, val y: Double, val z: Double){
	fun toVec3d(): Vec3d = this
	fun toVec3f(): Vec3f = Vec3f(x.toFloat(), y.toFloat(), z.toFloat())
	fun toVec3l(): Vec3l = Vec3l(x.toLong(), y.toLong(), z.toLong())
	fun toVec3i(): Vec3i = Vec3i(x.toInt(), y.toInt(), z.toInt())

	operator fun plus(vec: Vec3d) = Vec3d(x + vec.x, y + vec.y, z + vec.z)
	operator fun minus(vec: Vec3d) = Vec3d(x - vec.x, y - vec.y, z - vec.z)
	operator fun times(other: Double) = Vec3d(x * other, y * other, z * other)
	operator fun div(other: Double) = Vec3d(x / other, y / other, z / other)
}

/**
 * 3 dimensional vector of float
 */
data class Vec3f constructor(val x: Float, val y: Float, val z: Float){
	fun toVec3d(): Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
	fun toVec3f(): Vec3f = this
	fun toVec3l(): Vec3l = Vec3l(x.toLong(), y.toLong(), z.toLong())
	fun toVec3i(): Vec3i = Vec3i(x.toInt(), y.toInt(), z.toInt())

	operator fun plus(vec: Vec3f) = Vec3f(x + vec.x, y + vec.y, z + vec.z)
	operator fun minus(vec: Vec3f) = Vec3f(x - vec.x, y - vec.y, z - vec.z)
	operator fun times(other: Float) = Vec3f(x * other, y * other, z * other)
	operator fun div(other: Float) = Vec3f(x / other, y / other, z / other)
}

/**
 * 3 dimensional vector of long
 */
data class Vec3l constructor(val x: Long, val y: Long, val z: Long){
	fun toVec3d(): Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
	fun toVec3f(): Vec3f = Vec3f(x.toFloat(), y.toFloat(), z.toFloat())
	fun toVec3l(): Vec3l = this
	fun toVec3i(): Vec3i = Vec3i(x.toInt(), y.toInt(), z.toInt())

	operator fun plus(vec: Vec3l) = Vec3l(x + vec.x, y + vec.y, z + vec.z)
	operator fun minus(vec: Vec3l) = Vec3l(x - vec.x, y - vec.y, z - vec.z)
	operator fun times(other: Long) = Vec3l(x * other, y * other, z * other)
	operator fun div(other: Long) = Vec3l(x / other, y / other, z / other)
}

/**
 * 3 dimensional vector of int
 */
data class Vec3i constructor(val x: Int, val y: Int, val z: Int) {
	val up get() = Vec3i(x, y + 1, z)
	val down get() = Vec3i(x, y - 1, z)
	val west get() = Vec3i(x - 1, y, z)
	val east get() = Vec3i(x + 1, y, z)
	val south get() = Vec3i(x, y, z + 1)
	val north get() = Vec3i(x, y, z - 1)
	val directions get() = listOf(up, down, west, east, south, north)

	fun toVec3d(): Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
	fun toVec3f(): Vec3f = Vec3f(x.toFloat(), y.toFloat(), z.toFloat())
	fun toVec3l(): Vec3l = Vec3l(x.toLong(), y.toLong(), z.toLong())
	fun toVec3i(): Vec3i = this

	operator fun plus(vec: Vec3i) = vec3Of(x + vec.x, y + vec.y, z + vec.z)
	operator fun minus(vec: Vec3i) = vec3Of(x - vec.x, y - vec.y, z - vec.z)
	operator fun times(other: Int) = vec3Of(x * other, y * other, z * other)
	operator fun div(other: Int) = vec3Of(x / other, y / other, z / other)
}

@Deprecated("", replaceWith = ReplaceWith("Vec3d(x, y, z)", "com.anatawa12.libs.vector.Vec3d"))
fun vec3Of(x: Double, y: Double, z: Double): Vec3d = Vec3d(x, y, z)

@Deprecated("", replaceWith = ReplaceWith("Vec3f(x, y, z)", "com.anatawa12.libs.vector.Vec3f"))
fun vec3Of(x: Float, y: Float, z: Float): Vec3f = Vec3f(x, y, z)

@Deprecated("", replaceWith = ReplaceWith("Vec3l(x, y, z)", "com.anatawa12.libs.vector.Vec3l"))
fun vec3Of(x: Long, y: Long, z: Long): Vec3l = Vec3l(x, y, z)

@Deprecated("", replaceWith = ReplaceWith("Vec3i(x, y, z)", "com.anatawa12.libs.vector.Vec3i"))
fun vec3Of(x: Int, y: Int, z: Int): Vec3i = Vec3i(x, y, z)