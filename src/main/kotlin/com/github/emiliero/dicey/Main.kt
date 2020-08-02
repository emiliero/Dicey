package com.github.emiliero.dicey

import com.github.emiliero.dicey.authenticate.login
import com.github.emiliero.dicey.controller.adminCommands
import com.github.emiliero.dicey.controller.commands
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder
import discord4j.core.`object`.presence.Activity
import discord4j.core.`object`.presence.Presence


fun main(args: Array<String>) {
    val client: DiscordClient = DiscordClientBuilder("NjY0NDg1NDE4NzgzMzQyNTky.XkRXzQ.20YpdahptEj14Qa-KJyON6nGSwc")
        .setInitialPresence(Presence.online(Activity.listening(Commands.Cmds.command)))
        .build()

    login(client)
    adminCommands(client)
    commands(client)
    client.login().block()
}
