package com.github.emiliero.dicey

import com.github.emiliero.dicey.authenticate.login
import com.github.emiliero.dicey.handler.inputYesNo
import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder

fun main(args: Array<String>) {
    val client: DiscordClient = DiscordClientBuilder(BuildConfig.TOKEN_KEY).build()
    login(client)

    inputYesNo(client)
    client.login().block()
}
