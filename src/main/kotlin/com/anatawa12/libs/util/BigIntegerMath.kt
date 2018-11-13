@file:JvmName("BigIntegerMath")
package com.anatawa12.libs.util

import java.math.BigInteger

/**
 * Created by anatawa12 on 2017/09/23.
 */

/**
 * [kotlin.math.pow] for [BigInteger]
 */
fun BigInteger.pow(b: BigInteger): BigInteger {
    var bigInteger = this
    val i = BigInteger.ONE
    while (i < b) {
        bigInteger *= (this)
        i.add(BigInteger.ONE)
    }
    return bigInteger
}