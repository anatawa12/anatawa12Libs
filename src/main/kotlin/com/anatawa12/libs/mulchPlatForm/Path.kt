package com.anatawa12.libs.mulchPlatForm

import com.anatawa12.libs.util.replaceAChars
import java.io.File

/**
 * Created by anatawa12 on 2018/03/13.
 */
/**
 * convert to path for platform path from '/' separated path (UNIX like to platform)
 */
fun String.asPlatformPath() = this.replace('/', File.separatorChar)

/**
 * convert to path list for platform path list from ':' separated path list (UNIX like to platform)
 */
fun String.asPlatformPathList() = this.replaceAChars("/:", "${File.separatorChar}${File.pathSeparatorChar}")
