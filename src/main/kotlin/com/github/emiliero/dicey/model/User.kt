package com.github.emiliero.dicey.model

class User(val name: String, val discriminator: String, val snowflake: String) {
    override fun toString(): String {
        return name
    }
}
