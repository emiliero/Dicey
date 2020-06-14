package com.github.emiliero.dicey.handler

enum class Commands(var commandString: String) {
    Tuck("/tuck"){
        override fun toString(): String {
            return "Tuck someone in to say good night."
        }
    }
}
