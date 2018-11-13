package com.anatawa12.libs.logging

import java.io.File
import java.io.PrintStream

/**
 * Created by anatawa12 on 2017/11/12.
 */

/**
 * A PrintStream write to [file] and [that]
 */
class MultiPrintStream(file: File, private val that: PrintStream?) : PrintStream(file) {
	override fun print(b: Boolean) {
		super.print(b)
		that?.print(b)
	}

	override fun print(c: Char) {
		super.print(c)
		that?.print(c)
	}

	override fun print(s: CharArray) {
		super.print(s)
		that?.print(s)
	}

	override fun print(d: Double) {
		super.print(d)
		that?.print(d)
	}

	override fun print(f: Float) {
		super.print(f)
		that?.print(f)
	}

	override fun print(i: Int) {
		super.print(i)
		that?.print(i)
	}

	override fun print(l: Long) {
		super.print(l)
		that?.print(l)
	}

	override fun print(s: String?) {
		super.print(s)
		that?.print(s)
	}

	override fun print(obj: Any?) {
		super.print(obj)
		that?.print(obj)
	}

	override fun println() {
		super.println()
		that?.println()
	}

	override fun println(b: Boolean) {
		print(b)
		println()
	}

	override fun println(c: Char) {
		print(c)
		println()
	}

	override fun println(s: CharArray) {
		print(s)
		println()
	}

	override fun println(d: Double) {
		print(d)
		println()
	}

	override fun println(f: Float) {
		print(f)
		println()
	}

	override fun println(i: Int) {
		print(i)
		println()
	}

	override fun println(l: Long) {
		print(l)
		println()
	}

	override fun println(s: String?) {
		print(s)
		println()
	}

	override fun println(obj: Any?) {
		print(obj)
		println()
	}
}