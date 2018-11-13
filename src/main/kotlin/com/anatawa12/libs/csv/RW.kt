package com.anatawa12.libs.csv

import java.io.BufferedReader
import kotlin.coroutines.experimental.buildSequence

/**
 * Created by anatawa12 on 2018/03/04.
 */
/**
 * read csv
 */
fun readCsv(reader: BufferedReader, separater: Char = ',', quote: Char? = null): Sequence<Sequence<String>> = buildSequence {
	reader.lineSequence().forEach {
		yield(
				buildSequence {
					var builder = StringBuilder()
					var state = State.Start
					for (c in it) {
						when (state) {
							State.Start -> {
								when (c) {
									quote -> {
										state = State.InQuote
									}
									separater -> {
										yield("")
									}
									else -> {
										state = State.OutQuote
										builder.append(c)
									}
								}
							}
							State.InQuote -> {
								when (c) {
									quote -> {
										state = State.AfterQuote
									}
									else -> builder.append(c)
								}
							}
							State.OutQuote -> {
								when (c) {
									separater -> {
										yield(builder.toString())
										builder = StringBuilder()
										state = State.Start
									}
									else -> builder.append(c)
								}
							}
							State.AfterQuote -> {
								when (c) {
									separater -> {
										yield(builder.toString())
										builder = StringBuilder()
										state = State.Start
									}
									quote -> {
										state = State.InQuote
										builder.append(quote)
									}
									else -> error("illegal csv")
								}
							}
						}
					}
					yield(builder.toString())
				}
		)
	}
}

private enum class State {
	InQuote,
	AfterQuote,
	OutQuote,
	Start
}

/**
 * two-dimensional array to csv
 */
fun <T>Array<Array<T>>.toCsvString(): String = this.joinToString("\n","") { iterable -> iterable.joinTo(StringBuilder(), prefix = "\"", separator = "\",\"", postfix = "\""){ it.toString().replace("\"", "\"\"") } }
