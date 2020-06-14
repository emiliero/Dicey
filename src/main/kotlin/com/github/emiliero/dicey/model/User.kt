package com.github.emiliero.dicey.model

import discord4j.core.`object`.util.Snowflake

class User(val name: String, val snowflake: Snowflake) {
    override fun toString(): String {
        return name
    }
}
