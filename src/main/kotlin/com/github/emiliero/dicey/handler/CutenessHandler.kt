package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.model.output.Cuteness
import discord4j.core.`object`.util.Snowflake
import java.security.SecureRandom

fun detectListSize(taggedUsers: List<Snowflake>): String {
    return if (taggedUsers.isEmpty())
        "C"
    else
        "'s c"
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
