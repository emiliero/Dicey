package com.github.emiliero.dicey

import com.github.emiliero.dicey.authenticate.login
import com.github.emiliero.dicey.controller.commands
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder
import discord4j.core.`object`.presence.Activity
import discord4j.core.`object`.presence.Presence


fun main(args: Array<String>) {
    val client: DiscordClient = DiscordClientBuilder(BuildConfig.TOKEN_KEY)
        .setInitialPresence(Presence.online(Activity.listening(Commands.Cmds.command)))
        .build()

    login(client)
    commands(client)
    client.login().block()
}
