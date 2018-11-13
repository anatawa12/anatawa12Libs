package com.anatawa12.libs.util

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by anatawa12 on 2018/11/11.
 */
class Base64Test {
	private val ary = byteArrayOf(
			0x1b.toByte(), 0xf1.toByte(), 0x86.toByte(), 0x6c.toByte(), 0xce.toByte(), 0x92.toByte(), 0xd3.toByte(), 0x79.toByte(), 0xb0.toByte(), 0xd7.toByte(), 0x7a.toByte(), 0x10.toByte(), 0x13.toByte(), 0xc0.toByte(), 0x5e.toByte(), 0x95.toByte(),
			0xb3.toByte(), 0x33.toByte(), 0x39.toByte(), 0x5a.toByte(), 0x09.toByte(), 0xda.toByte(), 0xcf.toByte(), 0xed.toByte(), 0x03.toByte(), 0xfc.toByte(), 0x0e.toByte(), 0xb3.toByte(), 0xe7.toByte(), 0xb3.toByte(), 0x78.toByte(), 0x45.toByte(),
			0x13.toByte(), 0x1d.toByte(), 0x22.toByte(), 0xd3.toByte(), 0x78.toByte(), 0x3c.toByte(), 0xf2.toByte(), 0x5a.toByte(), 0x2f.toByte(), 0x78.toByte(), 0x06.toByte(), 0x0c.toByte(), 0x5c.toByte(), 0x09.toByte(), 0xaf.toByte(), 0x1a.toByte(),
			0x1f.toByte(), 0x64.toByte(), 0x2f.toByte(), 0xc9.toByte(), 0x2c.toByte(), 0xeb.toByte(), 0xb2.toByte(), 0x47.toByte(), 0x48.toByte(), 0xb5.toByte(), 0x3f.toByte(), 0xda.toByte(), 0x8b.toByte(), 0x19.toByte(), 0xb4.toByte(), 0xce.toByte(),
			0xb5.toByte(), 0x63.toByte(), 0x4a.toByte(), 0xbf.toByte(), 0x6c.toByte(), 0xf1.toByte(), 0x5a.toByte(), 0xbb.toByte(), 0xb4.toByte(), 0x0f.toByte(), 0x4f.toByte(), 0x32.toByte(), 0x14.toByte(), 0xbd.toByte(), 0xa1.toByte(), 0xf5.toByte(),
			0xeb.toByte(), 0x79.toByte(), 0x56.toByte(), 0x8f.toByte(), 0xa5.toByte(), 0xdc.toByte(), 0x99.toByte(), 0xdf.toByte(), 0x0d.toByte(), 0x92.toByte(), 0x0c.toByte(), 0x2d.toByte(), 0x2f.toByte(), 0xb7.toByte(), 0x6a.toByte(), 0xa8.toByte(),
			0x9a.toByte(), 0x97.toByte(), 0x1f.toByte(), 0xf1.toByte(), 0x9e.toByte(), 0x6f.toByte(), 0xb9.toByte(), 0x8e.toByte(), 0x4c.toByte(), 0xfa.toByte(), 0x87.toByte(), 0x32.toByte(), 0x00.toByte(), 0x3e.toByte(), 0x8f.toByte(), 0xa0.toByte(),
			0x20.toByte(), 0xc4.toByte(), 0x23.toByte(), 0xfe.toByte(), 0xef.toByte(), 0xd4.toByte(), 0x7c.toByte(), 0xed.toByte(), 0x23.toByte(), 0x6d.toByte(), 0x55.toByte(), 0x20.toByte(), 0x2e.toByte(), 0x34.toByte(), 0x21.toByte(), 0x88.toByte(),
			0x60.toByte(), 0x99.toByte(), 0x8a.toByte(), 0x7b.toByte(), 0xb3.toByte(), 0x04.toByte(), 0x9c.toByte(), 0x0e.toByte(), 0xa9.toByte(), 0x7b.toByte(), 0x67.toByte(), 0x9c.toByte(), 0xb5.toByte(), 0xe7.toByte(), 0xd9.toByte(), 0x8c.toByte(),
			0x0d.toByte(), 0xfc.toByte(), 0x49.toByte(), 0xc7.toByte(), 0x7c.toByte(), 0x51.toByte(), 0xa3.toByte(), 0xbf.toByte(), 0x3c.toByte(), 0x5f.toByte(), 0xd1.toByte(), 0xb1.toByte(), 0xfb.toByte(), 0x69.toByte(), 0xcc.toByte(), 0x8e.toByte(),
			0x28.toByte(), 0x94.toByte(), 0xa7.toByte(), 0x7f.toByte(), 0x87.toByte(), 0x50.toByte(), 0x0a.toByte(), 0xbf.toByte(), 0xa2.toByte(), 0x8f.toByte(), 0x16.toByte(), 0x95.toByte(), 0x21.toByte(), 0xd5.toByte(), 0x5c.toByte(), 0xd8.toByte(),
			0xc6.toByte(), 0xc9.toByte(), 0x3a.toByte(), 0xc1.toByte(), 0x30.toByte(), 0x91.toByte(), 0xd4.toByte(), 0xe8.toByte(), 0x71.toByte(), 0x17.toByte(), 0x5b.toByte(), 0xc0.toByte(), 0x9c.toByte(), 0x12.toByte(), 0xd6.toByte(), 0x0e.toByte(),
			0x16.toByte(), 0x4a.toByte(), 0x8b.toByte(), 0x8f.toByte(), 0xa7.toByte(), 0xd7.toByte(), 0x41.toByte(), 0x45.toByte(), 0xb1.toByte(), 0x4a.toByte(), 0x1a.toByte(), 0xbf.toByte(), 0x03.toByte(), 0xf7.toByte(), 0x7c.toByte(), 0xae.toByte(),
			0xa2.toByte(), 0x58.toByte(), 0xa4.toByte(), 0x53.toByte(), 0xe4.toByte(), 0xce.toByte(), 0x3a.toByte(), 0x2b.toByte(), 0xbf.toByte(), 0xc3.toByte(), 0x3f.toByte(), 0x23.toByte(), 0x29.toByte(), 0xc7.toByte(), 0x98.toByte(), 0xb3.toByte(),
			0xd5.toByte(), 0x70.toByte(), 0xb5.toByte(), 0x82.toByte(), 0xd8.toByte(), 0x65.toByte(), 0x82.toByte(), 0xe1.toByte(), 0x73.toByte(), 0xca.toByte(), 0xd8.toByte(), 0x84.toByte(), 0x6e.toByte(), 0x7a.toByte(), 0xd6.toByte(), 0x28.toByte(),
			0xbd.toByte(), 0xfb.toByte(), 0x5c.toByte(), 0x69.toByte(), 0xe4.toByte(), 0x4b.toByte(), 0x83.toByte(), 0xd5.toByte(), 0x85.toByte(), 0x89.toByte(), 0x52.toByte(), 0xc8.toByte(), 0x52.toByte(), 0x4e.toByte(), 0xed.toByte(), 0xe3.toByte()
	)
	private val aryBase64 = "G/GGbM6S03mw13oQE8BelbMzOVoJ2s/tA/wOs+ezeEUTHSLTeDzyWi94BgxcCa8a" +
			"H2QvySzrskdItT/aixm0zrVjSr9s8Vq7tA9PMhS9ofXreVaPpdyZ3w2SDC0vt2qo" +
			"mpcf8Z5vuY5M+ocyAD6PoCDEI/7v1HztI21VIC40IYhgmYp7swScDql7Z5y159mM" +
			"DfxJx3xRo788X9Gx+2nMjiiUp3+HUAq/oo8WlSHVXNjGyTrBMJHU6HEXW8CcEtYO" +
			"FkqLj6fXQUWxShq/A/d8rqJYpFPkzjorv8M/IynHmLPVcLWC2GWC4XPK2IRuetYo" +
			"vftcaeRLg9WFiVLIUk7t4w"
	private val aryBase64Pat = aryBase64 + "=="
	@Test
	fun decodeTest() {
		assertArrayEquals(aryBase64Pat.fromBase64(), ary)

		assertArrayEquals(aryBase64.fromBase64(null), ary)
	}
	@Test(expected = IllegalArgumentException::class)
	fun decodeErrorTest1() {
		aryBase64Pat.fromBase64(null).joinToString(""){ "%02x".format(it.toInt() and 0xFF) }
	}

	@Test
	fun encodeTest() {
		assertEquals(ary.toBase64(), aryBase64)
		assertEquals(ary.toBase64(padding = '='), aryBase64Pat)
	}
}