package com.github.emiliero.dicey.handler

import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.message.MessageCreateEvent
import java.security.SecureRandom

fun inputYesNo(client: DiscordClient) {
    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { message: Message ->
            message.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { message: Message ->
            message.content.orElse("").contains("!yesno", ignoreCase = true)
        }
        .flatMap<MessageChannel> { obj: Message -> obj.channel }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(randomNumberGenerator(100)) }
        .subscribe()
}

private fun randomNumberGenerator(maxNum: Int): String {
    val secureRandom = SecureRandom().nextInt(maxNum)

    return if (secureRandom >= 50) {
        "yes"
    } else {
        "no"
    }
}
