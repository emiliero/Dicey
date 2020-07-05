package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.databuilders.getMessageAuthor
import com.github.emiliero.dicey.handler.*
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.message.MessageCreateEvent

// TODO: Optimize member tagging
fun commands(client: DiscordClient) {
    inputCmds(client)
    inputTuck(client)
    inputLove(client)
    inputRoll(client)
    inputCuteness(client)
}

private fun inputCmds(client: DiscordClient) {
    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { message: Message ->
            message.author.map { user: User -> !user.isBot}.orElse(false)
        }
        .filter { message: Message ->
            message.content.orElse("").contains(Commands.Cmds.command, ignoreCase = true)
        }.flatMap { m : Message -> m.channel }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(printCommands())}
        .subscribe()
}

private fun inputTuck(client: DiscordClient) {
    var author = ""
    var message = ""
    // peepopat: <:peepoPat:616932471778574356>

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").startsWith(Commands.Tuck.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            message = m.content.get()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "<@$author> tucks ${checkType(message)}"
        )}
        .subscribe()
}

private fun inputLove(client: DiscordClient) {
    var author = ""
    var message = ""
    // SakamotoLove: 604425012744683540
    //<:YEPW:687976516495081555>

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").startsWith(Commands.Love.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            message = m.content.get()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "<@$author> loves ${generateLovePercentage(message)}."
        )}
        .subscribe()
}


private fun inputRoll(client: DiscordClient) {
    var author = ""
    var message = ""

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").startsWith(Commands.Roll.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            message = m.content.get()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "${generateRandomNumber(message)}, <@$author>"
        )}
        .subscribe()
}

private fun inputCuteness(client: DiscordClient) {
    var author = ""
    var message = ""

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Cute.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            message = m.content.get()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "Cuteness level: ${generateCutenessLevel()}, <@$author>"
        )}
        .subscribe()
}



