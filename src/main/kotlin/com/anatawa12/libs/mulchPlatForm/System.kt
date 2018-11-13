package com.anatawa12.libs.mulchPlatForm

/**
 * Created by anatawa12 on 2018/03/13.
 */
private val osArch by lazy { System.getProperty("os.arch"); }

/**
 * if the cpu is amd64 or x86_64, return true
 */
val is64bit by lazy {
	return@lazy "amd64".equals(osArch) || "x86_64".equals(osArch)
}

/**
 * platform kind
 */
val platform by lazy {
	if ( osArch.startsWith("Windows") )
		return@lazy OS.Windows
	else if ( osArch.startsWith("Linux") || osArch.startsWith("FreeBSD") || osArch.startsWith("OpenBSD") || osArch.startsWith("SunOS") || osArch.startsWith("Unix") )
		return@lazy OS.Linux
	else if ( osArch.startsWith("Mac OS X") || osArch.startsWith("Darwin") )
		return@lazy OS.MacOS
	return@lazy OS.Other
}

fun load(name: String, postfix64Bit: String) {
	if (!platform.canLoad) error("don't support os")
	val try64First = platform != OS.MacOS && is64bit
	var err: Error? = null;
	if (try64First) {
		try {
			System.load(name + postfix64Bit + platform.dynamicLibraryExtension)
			return;
		} catch (e: UnsatisfiedLinkError) {
			err = e
		}
	}

	try {
		System.load(name)
	} catch (e: UnsatisfiedLinkError) {
		if (try64First) throw err!!;
		System.load(name + postfix64Bit + platform.dynamicLibraryExtension)
	}
}

fun loadLibrary(name: String, postfix64Bit: String) {
	if (!platform.canLoad) error("don't support os")
	val try64First = platform != OS.MacOS && is64bit
	var err: Error? = null;
	if (try64First) {
		try {
			System.loadLibrary(name + postfix64Bit)
			return;
		} catch (e: UnsatisfiedLinkError) {
			err = e
		}
	}

	try {
		System.loadLibrary(name)
	} catch (e: UnsatisfiedLinkError) {
		if (try64First) throw err!!;
		System.loadLibrary(name + postfix64Bit)
	}
}

enum class OS {
	Linux {
		override val canLoad: Boolean get() = true
		override val dynamicLibraryExtension: String get() = ".so"
	},
	Windows {
		override val canLoad: Boolean get() = true
		override val dynamicLibraryExtension: String get() = ".dll"
	},
	MacOS {
		override val canLoad: Boolean get() = true
		override val dynamicLibraryExtension: String get() = ".dylib"
	},
	Other {
		override val canLoad: Boolean get() = false
		override val dynamicLibraryExtension: String get() = error("don't support os")
	}
	;
	abstract val canLoad: Boolean
	abstract val dynamicLibraryExtension: String
}
