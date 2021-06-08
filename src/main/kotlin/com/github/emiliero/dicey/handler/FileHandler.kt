package com.github.emiliero.dicey.handler

import java.io.File

fun readFile(filePath: String): String {
    val fileStream = File(filePath).inputStream()

    return fileStream.readBytes().toString(Charsets.UTF_8)
}

fun writeAdditionToFile(filePath: String, addition: String) {
    val fileStream = File(filePath).outputStream().writer(Charsets.UTF_8)
    fileStream.write(addition + '\n', 0, addition.length)
}