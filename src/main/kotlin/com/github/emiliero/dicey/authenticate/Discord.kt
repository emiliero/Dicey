package com.github.emiliero.dicey.authenticate

import discord4j.core.DiscordClient
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent

fun login(client: DiscordClient) {
    client.eventDispatcher
        .on(MessageCreateEvent::class.java)
        .subscribe { event: MessageCreateEvent ->
            event.message.content.ifPresent { c: String? -> println(c) }
        }

    loginResult(client)
}

private fun loginResult(client: DiscordClient) {
    client.eventDispatcher.on(ReadyEvent::class.java)
        .subscribe { event: ReadyEvent ->
            val self = event.self
            println(java.lang.String.format("Logged in as %s#%s", self.username, self.discriminator))
        }
}
