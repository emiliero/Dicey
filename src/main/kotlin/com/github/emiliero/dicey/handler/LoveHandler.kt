package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.databuilders.generateCommandMessageIntoArray
import com.github.emiliero.dicey.databuilders.generateStringFromCommandArrayAfterCommand
import java.security.SecureRandom

fun generateLovePercentage(commandMessage: String): String {
    val messageWordArray = generateCommandMessageIntoArray(commandMessage)
    val secureRandom = SecureRandom().nextInt((100 - 0) + 1)

    return if (messageWordArray.size >= 2) {
        "${generateStringFromCommandArrayAfterCommand(messageWordArray)} $secureRandom%"
    } else {
        "nothing"
    }
}
