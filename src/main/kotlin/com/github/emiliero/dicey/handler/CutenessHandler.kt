package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.model.Cuteness
import java.security.SecureRandom

fun generateCutenessLevel(): String {
    val secureRandom = SecureRandom().nextInt((100 - 0) + 1)

    return when (secureRandom) {
        in 1..8 -> Cuteness.Cute5.level
        in 9..32 -> Cuteness.Cute4.level
        in 33..68 -> Cuteness.Cute3.level
        in 69..92 -> Cuteness.Cute2.level
        in 93..100 -> Cuteness.Cute1.level
        else -> "Cute"
    }
}
