package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.handler.generatePatchNotes
import com.github.emiliero.dicey.handler.getPatchNotes
import com.github.emiliero.dicey.handler.printCommands
import com.github.emiliero.dicey.handler.readFile
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.message.MessageCreateEvent
import java.awt.Color
import java.nio.file.Paths
import java.util.logging.FileHandler


fun adminCommands(client: DiscordClient) {
    inputCmds(client)
    inputPatchNotes(client)
}

private fun inputCmds(client: DiscordClient) {
    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map { obj: MessageCreateEvent -> obj.message }
        .filter { message: Message ->
            message.author.map { user: User -> !user.isBot}.orElse(false)
        }
        .filter { message: Message ->
            message.content.orElse("").contains(Commands.Cmds.command, ignoreCase = true)
        }
        .flatMap { m : Message -> m.channel }
        .flatMap<Message> { channel: MessageChannel -> channel.createMessage(printCommands())}
        .subscribe()
}

private fun inputPatchNotes(client: DiscordClient) {
    client.eventDispatcher.on(MessageCreateEvent::class.java)
        .map{ obj: MessageCreateEvent -> obj.message }
        .filter { message: Message ->
            message.author.map { user: User -> !user.isBot}.orElse(false)
        }
        .filter { message: Message ->
            message.content.orElse("").contains(Commands.Patch.command, ignoreCase = true)
        }
        .flatMap { m : Message -> m.channel }
        .flatMap<Message> { channel: MessageChannel ->
            channel.createMessage { messageCreateSpec ->
                messageCreateSpec.setEmbed { embedCreateSpec ->
                    val patchNote = getPatchNotes().getLatestPatchNote()
                    embedCreateSpec
                        .setTitle("Patch notes")
                        .setColor(Color.decode("#5EB0AE"))
                        .setDescription(generatePatchNotes(patchNote))
                        .addField("Version", patchNote.version, true)
                        .addField("Date", patchNote.date, true)
                }
            }
        }
        .subscribe()
}
