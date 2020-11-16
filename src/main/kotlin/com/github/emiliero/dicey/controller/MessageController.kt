package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.databuilders.fetchTaggedUsers
import com.github.emiliero.dicey.databuilders.getMessageAuthor
import com.github.emiliero.dicey.handler.*
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.`object`.util.Snowflake
import discord4j.core.event.domain.message.MessageCreateEvent

// TODO: Optimize member tagging
fun commands(client: DiscordClient) {
    inputTuck(client)
    inputPat(client)
    inputBonk(client)

    inputSpank(client)
    inputLove(client)
    inputRoll(client)
    inputCuteness(client)
}

private fun inputPat(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Pat.command + " ", ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":smiling_face_with_3_hearts: <@!$author> pats ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "you"
            } gently."
        )}
        .subscribe()
}

private fun inputBonk(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Bonk.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":cop: <@!$author> bonks ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "you"
            } :head_bandage: :hammer:"
        )}
        .subscribe()
}

private fun inputSpank(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Spank.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "<@!$author> spanks ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "you"
            } :peach: :flushed:"
        )}
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
            m.content.orElse("").contains(Commands.Tuck.command, ignoreCase = true)
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
    var taggedUsers = emptyList<Snowflake>()
    //var taggedRoles = emptyList<Snowflake>()
    //var message = ""

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
            //message = m.content.get()
            taggedUsers = m.userMentionIds.distinct()
            //taggedRoles = m.roleMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "${fetchTaggedUsers(taggedUsers)} " +
                    "${detectListSize(taggedUsers)}uteness level: " +
                    "${generateCutenessLevel()}, <@$author>"
        )}
        .subscribe()
}



