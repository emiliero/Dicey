package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.databuilders.generateCommandMessageIntoArray
import java.security.SecureRandom
//TODO: Tweak so !roll can be at the end of a sentence

fun generateRandomNumber(cmdParameters: String): String {
    val range = handleRollCommandParameters(cmdParameters)

    return if (range.first() < 0 || range.last() <= 0 || range.last() >= 2147483647) {
        "Please choose a maximum value, alternatively minimum and maximum value, between 1 and 2147483646"
    } else {
        val secureRandom = SecureRandom().nextInt((range[1] - range[0]) + 1) + range[0]
        secureRandom.toString()
    }
}

private fun handleRollCommandParameters(message: String): Array<Int> {
    val messageCharArray = generateCommandMessageIntoArray(message)
    var maxValue = 0
    var minValue = 0

    if (messageCharArray.size == 2) {
        maxValue = messageCharArray[1].toIntOrNull() ?: 0
    } else if (messageCharArray.size >= 3) {
        minValue = messageCharArray[1].toIntOrNull() ?: 0
        maxValue = messageCharArray[2].toIntOrNull() ?: 0
    }

    if (minValue > maxValue) {
        val temp = minValue
        minValue = maxValue
        maxValue = temp
    }

    return arrayOf(minValue, maxValue)
}
