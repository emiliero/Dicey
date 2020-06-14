package com.github.emiliero.dicey.model

enum class Commands(var command: String) {
    Cmds("!cmds"){
        override fun toString(): String {
            return "Lists out commands."
        }
    },
    Tuck("!tuck"){
        override fun toString(): String {
            return "Tuck someone in to say good night."
        }
    },
    Roll("!roll"){
        override fun toString(): String {
            return "Rolls a number between 0 and first value, or between first and second value."
        }
    }
}
