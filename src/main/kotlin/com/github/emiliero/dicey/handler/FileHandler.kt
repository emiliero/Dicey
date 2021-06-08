package com.github.emiliero.dicey.handler

import java.io.File

fun readFile(filePath: String): String {
    val fileStream = File(filePath).inputStream()

    return fileStream.readBytes().toString(Charsets.UTF_8)
}