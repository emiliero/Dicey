package com.github.emiliero.dicey.handler

import com.github.emiliero.dicey.model.Commands

fun printCommands() : String {
    var result : String = "**Dicey's list of commands** \n" +
            "I can do the following for you:\n\n"

    for(enum in Commands.values()) {
        result += "> `${checkCommand(enum.command)}` - $enum\n"
    }

    return result
}

private fun checkCommand(command: String): String {
    return when (command) {
        "!tuck" -> "$command [user | value]"
        "!love" -> "$command [user | values]"
        "!roll" -> "$command [maxValue | maxValue minValue | minValue maxValue]"
        else -> command
    }
}
