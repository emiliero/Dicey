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
    Pat("!pat"){
        override fun toString(): String {
            return "Pat someone gently."
        }
    },
    Spank("!spank"){
        override fun toString(): String {
            return "Spank someone."
        }
    },
    Love("!love"){
        override fun toString(): String {
            return "Calculates the love between messenger and first value."
        }
    },
    Roll("!roll"){
        override fun toString(): String {
            return "Rolls a number between 0 and first value, or between first and second value."
        }
    },
    Cute("!cute") {
        override fun toString(): String {
            return "Rates the cuteness of something/someone within five different levels."
        }
    }
}
