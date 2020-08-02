package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.handler.printCommands
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
import discord4j.core.`object`.Embed
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.message.MessageCreateEvent
import java.awt.Color


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
            message.content.orElse("").contains("!patch", ignoreCase = true)
        }
        .flatMap { m : Message -> m.channel }
        .flatMap<Message> { channel: MessageChannel ->
            channel.createMessage { messageCreateSpec ->
                messageCreateSpec.setEmbed { embedCreateSpec ->
                    embedCreateSpec
                        .setTitle("Patch notes")
                        .setColor(Color.decode("#5EB0AE"))
                        .setDescription("The following patch contains the following new commands:\n" +
                                "`!bonk` - Bonk someone for being too lewd.\n" +
                                "`!spank` - Spank someone.\n" +
                                "`!pat` - Pat someone gently.\n\n" +
                                "The following command has been tweaked:\n" +
                                "`!tuck` - The command output will now be generated with the entirety of the author's message.\n\n" +
                                "To view the full list of commands, type `!cmds`.")
                        .addField("Version", "0.3.0", true)
                        .addField("Date", "2 August 2020", true)
                }
            }
        }
        .subscribe()
}
