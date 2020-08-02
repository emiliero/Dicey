package com.github.emiliero.dicey.databuilders

import com.github.emiliero.dicey.model.User
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.util.Snowflake

fun getMessageAuthor(m: Message): User {
    val user = m.author.get().username
    val discriminator = m.author.get().discriminator
    val snowflake = m.author.get().id.toString().split("{", "}")[1]

    return User(user, discriminator, snowflake)
}

fun fetchUserSnowflakeInMessage(message: String): String {
    val start = "<"
    val end = ">"

    val startIndex = message.indexOf(start)
    val endIndex = message.indexOf(end)

    return if (startIndex >= 0 && endIndex >= 0) message.substring(startIndex, endIndex + 1) else "someone"
}

fun fetchTaggedUsers(tagged: List<Snowflake>): String {
    println(tagged.size)
    if (tagged == emptyList<Snowflake>()) {
        println("empty")
        return ""
    } else {
        println("full")
        return if (tagged.size == 1) {
            "<@!${tagged.first().asString()}>"
        } else {
            var result = ""
            for (n in tagged) {
                result +=  "<@!${n.asString()}>"
                result += when (tagged.lastIndex) {
                    tagged.indexOf(n) -> {
                        ""
                    }
                    tagged.indexOf(n)+1 -> {
                        " & "
                    }
                    else -> {
                        ", "
                    }
                }
            }
            result
        }
    }
}
