package com.anatawa12.libs.util

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by anatawa12 on 2018/11/11.
 */
class EscapeTest {
	val before = "07z89ik.?\$K@!K#S1`K\$(85)Z&368%X(BZY6KT!\$~\"::'!@W#W!@Q#\$\$%R%T^&&M*(KUP)*IOP)_{+_\"{(UL(*NM)LOUBJUHY&&UH*FGVCRT%\$EX@#\$!Q@#AZA\$@#!@W#\$%R^TYU&Y*N&O)\nx0i90-\t162\r\b\\\\"
	val after = "07z89ik.?\\\$K@!K#S1`K\\\$(85)Z&368%X(BZY6KT!\\\$~\\\"::\\'!@W#W!@Q#\\\$\\\$%R%T^&&M*(KUP)*IOP)_{+_\\\"{(UL(*NM)LOUBJUHY&&UH*FGVCRT%\\\$EX@#\\\$!Q@#AZA\\\$@#!@W#\\\$%R^TYU&Y*N&O)\\nx0i90-\\t162\\r\\b\\\\\\\\"
	@Test
	fun escape() {
		assertEquals(before.escape(), after)
	}

	@Test
	fun unescaape() {
		assertEquals(after.unescape(), before)
	}
}
/*
objc[35490]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/bin/java (0x106b054c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x107b0a4e0). One of the two will be used. Which one is undefined.
07z89ik.?$K@!K#S1`K$(85)Z&368%X(BZY6KT!$~"::'!@W#W!@Q#$$%R%T^&&M*(KUP)*IOP)_{+_"{(UL(*NM)LOUBJUHY&&UH*FGVCRT%$EX@#$!Q@#AZA$@#!@W#$%R^TYU&Y*N&O)
\\
// */
/*
07z89ik.?\$K@!K#S1`K\$(85)Z&368%X(BZY6KT!\$~\"::'!@W#W!@Q#\$\$%R%T^&&M*(KUP)*IOP)_{+_\"{(UL(*NM)LOUBJUHY&&UH*FGVCRT%\$EX@#\$!Q@#AZA\$@#!@W#\$%R^TYU&Y*N&O)\
x0i90-\	162\
\|b\\\\\\
// */

/*
07z89ik.?\$K@!K#S1`K\$(85)Z&368%X(BZY6KT!\$~\"::\'!@W#W!@Q#\$\$%R%T^&&M*(KUP)*IOP)_{+_\"{(UL(*NM)LOUBJUHY&&UH*FGVCRT%\$EX@#\$!Q@#AZA\$@#!@W#\$%R^TYU&Y*N&O)\nx0i90-\t162\r\b\\\\
// */
