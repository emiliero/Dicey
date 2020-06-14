package com.github.emiliero.dicey.model

enum class Commands(var commandString: String) {
    Tuck("!tuck"){
        override fun toString(): String {
            return "Tuck someone in to say good night."
        }
    }
}
