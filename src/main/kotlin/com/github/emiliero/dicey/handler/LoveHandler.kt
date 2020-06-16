package com.github.emiliero.dicey.handler

import java.security.SecureRandom

fun generateLovePercentage(cmdString: String): String {
    val messageWordArray = cmdString.split("\\s".toRegex())
    val secureRandom = SecureRandom().nextInt((100 - 0) + 1)

    return if (messageWordArray.size >= 2) {
        println(messageWordArray[1])
        "${messageWordArray[1]} $secureRandom%"
    } else {
        "nothing"
    }
}
