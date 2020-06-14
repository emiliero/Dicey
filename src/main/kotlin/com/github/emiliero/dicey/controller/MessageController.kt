package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.handler.fetchTaggedUserInMessage
import com.github.emiliero.dicey.handler.getMessageAuthor
import com.github.emiliero.dicey.handler.randomNumberGenerator
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.message.MessageCreateEvent

fun commands(client: DiscordClient) {
    inputYesNo(client)
    inputTuck(client)
}

private fun inputYesNo(client: DiscordClient) {
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

private fun inputTuck(client: DiscordClient) {
    var author = ""
    var message = ""
    // peepopat: <:peepoPat:616932471778574356>

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { message: Message ->
            message.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { message: Message ->
            message.content.orElse("").startsWith(Commands.Tuck.commandString, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            message = m.content.toString()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "<@$author> tucks ${fetchTaggedUserInMessage(message)}")
        }
        .subscribe()
}


