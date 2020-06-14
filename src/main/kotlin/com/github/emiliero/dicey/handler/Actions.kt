package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.model.User
import discord4j.core.`object`.entity.Message
import java.security.SecureRandom

fun randomNumberGenerator(maxNum: Int): String {
    val secureRandom = SecureRandom().nextInt(maxNum)

    return if (secureRandom >= 50) {
        "yes"
    } else {
        "no"
    }
}

fun getMessageAuthor(m: Message): User {
    val user = m.author.get().username
    val discriminator = m.author.get().discriminator
    val snowflake = m.author.get().id.toString().split("{", "}")[1]

    return User(user, discriminator, snowflake)
}

fun fetchTaggedUserInMessage(message: String): String {
    val start = "<"
    val end = ">"

    val startIndex = message.indexOf(start)
    val endIndex = message.indexOf(end)

    val snowflake = if (startIndex >= 0 && endIndex >= 0) message.substring(startIndex, endIndex + 1) else "someone"

    return checkType(snowflake)
}

private fun checkType(snowflake: String): String {
    return when {
        snowflake.contains("!", true) -> "in $snowflake"
        snowflake.contains("&", true) -> "in ${snowflake}s"
        else -> "you in"
    }
}
