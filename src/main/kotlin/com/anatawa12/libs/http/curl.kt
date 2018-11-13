package com.anatawa12.libs.http

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset


/**
 * Created by anatawa12 on 2018/01/22.
 */
/**
 * get data from [url]
 */
fun curl(url: URL, charset: Charset = Charsets.UTF_8): String {
	val sb = StringBuilder()
	var connection: HttpURLConnection? = null
	try {
		// コネクションをオープン
		connection = url.openConnection() as HttpURLConnection
		connection.requestMethod = "GET"
		// レスポンスが来た場合は処理続行
		if (connection.responseCode == HttpURLConnection.HTTP_OK) {
			// InputStreamを返す
			connection.inputStream.bufferedReader(charset).use {
				val cs = CharArray(256)
				while (true) {
					val size = it.read(cs)
					if (size == -1)
						return sb.toString()
					sb.append(cs, 0, size)
				}
			}
		}
		throw IOException()
	} finally {
		connection?.disconnect()
	}
}

fun curlToBytes(url: URL): ByteArray {
	val out = ByteArrayOutputStream()
	var connection: HttpURLConnection? = null
	try {
		// コネクションをオープン
		connection = url.openConnection() as HttpURLConnection
		connection.requestMethod = "GET"
		// レスポンスが来た場合は処理続行
		if (connection.responseCode == HttpURLConnection.HTTP_OK) {
			// InputStreamを返す
			connection.inputStream.copyTo(out)
			return out.toByteArray()
		}
		throw IOException()
	} finally {
		connection?.disconnect()
	}
}