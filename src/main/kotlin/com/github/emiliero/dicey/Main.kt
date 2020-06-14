package com.github.emiliero.dicey

import com.github.emiliero.dicey.authenticate.login
import com.github.emiliero.dicey.controller.commands
import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder

fun main(args: Array<String>) {
    val client: DiscordClient = DiscordClientBuilder(BuildConfig.TOKEN_KEY).build()
    login(client)

    commands(client)
    client.login().block()
}
