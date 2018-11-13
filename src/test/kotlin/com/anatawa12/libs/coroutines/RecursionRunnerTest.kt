package com.anatawa12.libs.coroutines

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigInteger

/**
 * Created by anatawa12 on 2018/07/12.
 */
class RecursionRunnerTest {
	@Test
	fun fact() {

		assertEquals(fact1(10.toBigInteger()), fact2(10.toBigInteger()))
	}

	fun fact1(n: BigInteger): BigInteger {
		return if (n == BigInteger.ZERO)
			BigInteger.ONE /* 脱出条件。0! は 1 である */
		else
			fact1(n - BigInteger.ONE) * n /* n! は (n-1)! に n を乗じたもの。再帰呼出し */
	}

	fun fact2(n: BigInteger): BigInteger {
		return recursionRun(n) { n ->
			if (n == BigInteger.ZERO)
				BigInteger.ONE /* 脱出条件。0! は 1 である */
			else
				call(n - BigInteger.ONE) * n /* n! は (n-1)! に n を乗じたもの。再帰呼出し */
		}
	}
}