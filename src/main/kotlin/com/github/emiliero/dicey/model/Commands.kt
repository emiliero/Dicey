package com.github.emiliero.dicey.model

enum class Commands(var command: String) {
    Bonk("!bonk"){
        override fun toString(): String {
            return "Bonk someone for being too lewd."
        }
    },
    Boop("!boop"){
        override fun toString(): String {
            return "Boop someone."
        }
    },
    Cmds("!cmds"){
        override fun toString(): String {
            return "Lists out commands."
        }
    },
    Cute("!cute") {
        override fun toString(): String {
            return "Rates the cuteness of something/someone within five different levels."
        }
    },
    Hug("!hug") {
        override fun toString(): String {
            return "Hug someone."
        }
    },
    Love("!love"){
        override fun toString(): String {
            return "Calculates the love between messenger and first value."
        }
    },
    Pat("!pat"){
        override fun toString(): String {
            return "Pat someone gently."
        }
    },
    Patch("!patch"){
        override fun toString(): String {
            return "Shows the recent changes to the bot."
        }
    },
    Poke("!poke"){
        override fun toString(): String {
            return "Poke someone."
        }
    },
    Roll("!roll"){
        override fun toString(): String {
            return "Rolls a number between 0 and first value, or between first and second value."
        }
    },
    Spank("!spank"){
        override fun toString(): String {
            return "Spank someone."
        }
    },
    Tuck("!tuck"){
        override fun toString(): String {
            return "Tuck someone in to say good night."
        }
    }
}
