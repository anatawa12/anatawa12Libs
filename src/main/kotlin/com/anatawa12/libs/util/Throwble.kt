package com.anatawa12.libs.util

import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter

/**
 * Created by anatawa12 on 2018/03/30.
 */
fun Throwable.getPrintMessage(): String {
	val out = ByteArrayOutputStream()
	val writer = OutputStreamWriter(out, Charsets.UTF_8)
	val printer = PrintWriter(writer)
	printStackTrace(printer)
	printer.flush()
	writer.flush()
	return out.toByteArray().toString(Charsets.UTF_8)
}
