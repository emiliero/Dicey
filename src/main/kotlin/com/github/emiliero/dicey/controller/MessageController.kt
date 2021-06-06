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

fun commands(client: DiscordClient) {
    inputTuck(client)
    inputPat(client)
    inputBonk(client)
    inputPoke(client)
    inputBoop(client)

    inputSpank(client)
    inputLove(client)
    inputRoll(client)
    inputCuteness(client)

    inputNewb(client)
    inputHug(client)
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

private fun inputPoke(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Poke.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            "<@!$author>  ${
                if (taggedUsers.isNotEmpty()) ":point_right: " + fetchTaggedUsers(taggedUsers) else "you"
            }"
        )}
        .subscribe()
}

private fun inputBoop(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Boop.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":point_right: :relaxed: <@!$author> ${
                if (taggedUsers.isNotEmpty()) " boops " + fetchTaggedUsers(taggedUsers) else "boops you"
            }"
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
            ":peach: :wave: <@!$author> spanks ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "you"
            } :flushed:"
        )}
        .subscribe()
}

private fun inputTuck(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()
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
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":last_quarter_moon_with_face: <@$author> tucks ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "you in"
            } :first_quarter_moon_with_face:"
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

private fun inputNewb(client: DiscordClient) {
    var author = ""

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains("NEWB", ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":cop: :raised_hand: Freeze! \n " +
            ":head_bandage: :hammer: <@!$author> is getting bonked for calling others a _NEWB_"
        )}
        .subscribe()
}

private fun inputHug(client: DiscordClient) {
    var author = ""
    var taggedUsers = emptyList<Snowflake>()

    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { m: Message ->
            m.author.map { user: User -> !user.isBot }.orElse(false)
        }
        .filter { m: Message ->
            m.content.orElse("").contains(Commands.Hug.command, ignoreCase = true)
        }
        .flatMap { m: Message ->
            author = getMessageAuthor(m).snowflake
            taggedUsers = m.userMentionIds.distinct()
            m.channel
        }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(
            ":hugging: <@$author> hugs ${
                if (taggedUsers.isNotEmpty()) fetchTaggedUsers(taggedUsers) else "themself"
            } :people_hugging:"
        )}
        .subscribe()
}