package com.anatawa12.libs.io

import java.util.*

/**
 * Created by anatawa12 on 2018/03/03.
 */

/**
 * read next string matched with [regex]
 */
fun Scanner.next(regex: Regex) = next(regex.toPattern())

/**
 * read to end
 */
fun Scanner.nextForEnd(): String{
	val builder = StringBuilder()
	while (hasNextLine()) {
		builder.append(nextLine())
		builder.append("\n")
	}
	return builder.toString()
}
