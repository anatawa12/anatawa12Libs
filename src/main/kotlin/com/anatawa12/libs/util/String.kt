package com.anatawa12.libs.util

import java.text.Normalizer

/**
 * Created by anatawa12 on 2018/03/13.
 */
/**
 * replace [froms] to [tos] for each char
 */
fun String.replaceAChars(froms: String, tos: String): String {
	require(froms.length == tos.length) { "froms's size is not equals to tos's size" }
	val chars = CharArray(length) { '\u0000' }
	this.forEachIndexed { index, c ->
		val fromIndex = froms.indexOf(c)
		if (fromIndex == -1) {
			chars[index] = c
		} else {
			chars[index] = tos[fromIndex]
		}
	}
	return String(chars)
}

/**
 * alias of [Normalizer.normalize]
 */
fun String.normalize(form: Normalizer.Form): String = Normalizer.normalize(this,Normalizer.Form.NFKC)

/**
 * for code gen
 */
fun String.escape(type: EscapeType = EscapeType.KotlinString): String {
	when (type) {
		EscapeType.KotlinString -> {
			val builder = StringBuilder()
			for (c in this) {
				when (c) {
					'\t' -> builder.append("\\t")
					'\b' -> builder.append("\\b")
					'\n' -> builder.append("\\n")
					'\r' -> builder.append("\\r")
					'\$' -> builder.append("\\$")
					'\'' -> builder.append("\\\'")
					'\"' -> builder.append("\\\"")
					'\\' -> builder.append("\\\\")
					else -> builder.append(c)
				}
			}
			return builder.toString()
		}
	}
}

/**
 * for code gen
 */
fun String.unescape(type: EscapeType = EscapeType.KotlinString): String {
	when (type) {
		EscapeType.KotlinString -> return this
				.replace("\\t", "\t")
				.replace("\\b", "\b")
				.replace("\\n", "\n")
				.replace("\\r", "\r")
				.replace("\\'", "\'")
				.replace("\\\"", "\"")
				.replace("\\$", "\$")
				.replace("\\\\", "\\")
	}
}

enum class EscapeType {
	KotlinString
}
