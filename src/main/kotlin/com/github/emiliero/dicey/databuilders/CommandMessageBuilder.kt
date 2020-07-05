package com.github.emiliero.dicey.databuilders

fun generateCommandMessageIntoArray(message: String): List<String> {
    return message.split("\\s".toRegex())
}

fun generateStringFromCommandArrayAfterCommand(array: List<String>): String {
    var result = ""

    array.forEachIndexed { i, e ->
        result += when (i) {
            0 -> return@forEachIndexed
            array.lastIndex -> e
            else -> "$e "
        }
    }

    return result
}
