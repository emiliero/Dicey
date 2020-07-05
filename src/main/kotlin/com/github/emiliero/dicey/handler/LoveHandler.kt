package com.github.emiliero.dicey.handler

import java.security.SecureRandom

fun generateLovePercentage(cmdString: String): String {
    val messageWordArray = cmdString.split("\\s".toRegex())
    val secureRandom = SecureRandom().nextInt((100 - 0) + 1)

    return if (messageWordArray.size >= 2) {
        val lastElementIndexOfMessage = messageWordArray.lastIndexOf(messageWordArray.last())
        val loveArray = messageWordArray.slice(1..lastElementIndexOfMessage)

        var loveString = ""
        for (e in loveArray)
            loveString += "$e "

        "$loveString$secureRandom%"
    } else {
        "nothing"
    }
}
