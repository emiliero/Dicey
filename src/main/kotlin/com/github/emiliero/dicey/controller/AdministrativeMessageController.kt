package com.github.emiliero.dicey.controller

import com.github.emiliero.dicey.handler.printCommands
import com.github.emiliero.dicey.model.Commands
import discord4j.core.DiscordClient
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
            message.content.orElse("").contains(Commands.Patch.command, ignoreCase = true)
        }
        .flatMap { m : Message -> m.channel }
        .flatMap<Message> { channel: MessageChannel ->
            channel.createMessage { messageCreateSpec ->
                messageCreateSpec.setEmbed { embedCreateSpec ->
                    embedCreateSpec
                        .setTitle("Patch notes")
                        .setColor(Color.decode("#5EB0AE"))
                        .setDescription("Newly added commands:\n" +
                                "`!poke` - Poke someone. This will only fetch mentioned users as of now.\n" +
                                "`NEWB` (both singular and plural) will cause the bot to bonk the message's author.\n\n" +
                                "Updates:\n" +
                                "`!pat` - You can now tuck several people together.\n" +
                                "`!pat`, `!spank`, `!bonk`, `!poke` and `!tuck` should work together.\n\n" +
                                "To view the full list of commands, type `!cmds`")
                        .addField("Version", "0.4.0", true)
                        .addField("Date", "16 November 2020", true)
                }
            }
        }
        .subscribe()
}
