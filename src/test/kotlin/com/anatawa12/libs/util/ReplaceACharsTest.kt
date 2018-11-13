package com.anatawa12.libs.util

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by anatawa12 on 2018/11/11.
 */
class ReplaceACharsTest {
	val before = "fhiuamxzjuhbiasijkhndxiwKLOIJKUkljmcKLMNOKLMOKLMOmklKLMOIJKNMbeloq90389u32657ioujiju7uy7tygvio9iu8fgrtydesw3aqswcdfgrbghnvjklmolop[;-=[]-=[]-=[]p[;ijkio89"
	val after = "fhi6amxzj6hbiasijkhidxiwKLOIJKUkljmcKLMNOKLMOKLMOmklKLMOIJKNMbeloq90389632657io6jij67677t7bvio9i68fbrt7desw3aqswcdfbrbbhivjklmolop[;-=[]-=[]-=[]p[;ijkio89"
	val changeBefore = "gnuy"
	val changeAfter  = "bi67"
	@Test
	fun test() {
		assertEquals(before.replaceAChars(changeBefore, changeAfter), after)
	}

	@Test(expected = IllegalArgumentException::class)
	fun errorTest1() {
		before.replaceAChars(changeBefore, "asdfas")
	}
}