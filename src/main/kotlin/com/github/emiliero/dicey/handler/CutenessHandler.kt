package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.model.Cuteness
import discord4j.core.`object`.util.Snowflake
import java.security.SecureRandom

fun detectListSize(taggedUsers: List<Snowflake>): String {
    return if (taggedUsers.isEmpty())
        "C"
    else
        "c"
}

fun fetchTaggedUsers(tagged: List<Snowflake>): String {
    println(tagged.size)
    if (tagged == emptyList<Snowflake>()) {
        println("empty")
        return ""
    } else {
        println("full")
        return if (tagged.size == 1) {
            "<@!${tagged.first().asString()}>'s"
        } else {
            var result = ""
            for (n in tagged) {
                result +=  "<@!${n.asString()}>"
                result += if (tagged.indexOf(n) == tagged.lastIndex) {
                    "'s"
                } else if(tagged.indexOf(n)+1 != tagged.lastIndex) {
                    ", "
                } else {
                    " & "
                }
            }
            result
        }
    }
}

fun generateCutenessLevel(): String {
    val secureRandom = SecureRandom().nextInt((100 - 0) + 1)

    return when (secureRandom) {
        in 1..10 -> Cuteness.Cute5.level
        in 11..34 -> Cuteness.Cute4.level
        in 35..65 -> Cuteness.Cute3.level
        in 66..90 -> Cuteness.Cute2.level
        in 91..100 -> Cuteness.Cute1.level
        else -> "Cute"
    }
}
