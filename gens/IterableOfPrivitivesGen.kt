fun main(args: Array<String>) {
	val primitives = setOf("Boolean", "Byte", "Short", "Int", "Long", "Float", "Double", "Char")
	println("package com.anatawa12.libs.collections")
	println()
	for (primitive in primitives) {
		println("import kotlin.collections.to${primitive}Array as ktTo${primitive}Array")
	}
	println()
	for (primitive in primitives) {
		println("fun Iterable<$primitive>.to${primitive}Array(): ${primitive}Array = (this as? Collection<$primitive>)?.ktTo${primitive}Array() ?: toList().ktTo${primitive}Array()")
	}
}