@file:JvmName("BigDecimalMath")
package com.anatawa12.libs.util

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * Created by anatawa12 on 2017/09/23.
 */

/**
 * [kotlin.math.log10] for [BigDecimal]
 */
@Suppress("INTEGER_OVERFLOW")
fun BigDecimal.log10(): BigDecimal {
    @Suppress("NAME_SHADOWING")
    var b = this
    val dp = Integer.MAX_VALUE
    val NUM_OF_DIGITS = dp + 2

    val mc = MathContext(NUM_OF_DIGITS, RoundingMode.HALF_EVEN)
    if (b.signum() <= 0)
        throw ArithmeticException("log of a negative number! (or zero)")
    else if (b.compareTo(BigDecimal.ONE) == 0)
        return BigDecimal.ZERO
    else if (b.compareTo(BigDecimal.ONE) < 0)
        return (BigDecimal.ONE.divide(b, mc)).log10().negate()

    val sb = StringBuffer()
    var leftDigits = b.precision() - b.scale()

    sb.append(leftDigits - 1).append(".")

    var n = 0
    while (n < NUM_OF_DIGITS) {
        b = b.movePointLeft(leftDigits - 1).pow(10, mc)
        leftDigits = b.precision() - b.scale()
        sb.append(leftDigits - 1)
        n++
    }

    var ans = BigDecimal(sb.toString())

    ans = ans.round(MathContext(ans.precision() - ans.scale() + dp, RoundingMode.HALF_EVEN))
    return ans
}
/*
infix fun BigDecimal.log(b: BigDecimal): BigDecimal {
    return log10(this).divide(log10(b))
}
// */

/**
 * [kotlin.math.log] for [BigDecimal]
 */
fun log(a: BigDecimal, b: BigDecimal): BigDecimal {
    return (a).log10() / (b.log10())
}

/**
 * [kotlin.math.pow] for [BigDecimal]
 */
fun pow(a: BigDecimal, b: BigDecimal): BigDecimal {
    return BigDecimal.ONE / (a.log10() * b).log10()
}


/**
 * [kotlin.math.floor] for [BigDecimal]
 */
fun floor(a: BigDecimal): BigDecimal {
    return a.setScale(0, BigDecimal.ROUND_DOWN)
}