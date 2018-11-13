package com.anatawa12.libs.lambda

/**
 * Created by anatawa12 on 2018/04/06.
 */
/**
 * first, call [this]. second, call [other]
 */
operator fun (()->Unit).plus(other: ()->Unit) = { this(); other() }

/**
 * first, call [this]. second, call [other]
 */
operator fun <T>((T)->Unit).plus(other: (T)->Unit) : (T)->Unit = { this(it); other(it) }
