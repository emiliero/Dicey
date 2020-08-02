package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.databuilders.fetchUserSnowflakeInMessage
import com.github.emiliero.dicey.databuilders.generateCommandMessageIntoArray
import com.github.emiliero.dicey.databuilders.generateStringFromCommandArrayAfterCommand

fun checkType(message: String): String {
    val snowflake = fetchUserSnowflakeInMessage(message)
    val messageArray = generateCommandMessageIntoArray(message)

    return when {
        snowflake.contains("!", true) -> "in $snowflake"
        snowflake.contains("&", true) -> "in ${snowflake}s"
        snowflake.contains("@", true) -> "in $snowflake"
        messageArray.size > 1 -> "in ${generateStringFromCommandArrayAfterCommand(messageArray)}"
        else -> message
    }
}
