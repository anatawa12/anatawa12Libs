package com.anatawa12.libs.collections

import java.util.*



/**
 * Created by anatawa12 on 2017/10/31.
 */

/**
 * linked hash map with max size
 * @param maxSize max size
 */
class LruCacheMap<K, V> (/** キャッシュエントリ最大数  */ @Volatile var maxSize: Int) : LinkedHashMap<K, V>(maxSize, 1f, true) {

	/** エントリの削除要否を判断  */
	override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
		return size > maxSize
	}
}
