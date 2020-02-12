package com.github.emiliero.dicey

import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.entity.User
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import java.security.SecureRandom



class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val client: DiscordClient = DiscordClientBuilder(BuildConfig.TOKEN_KEY).build()
            logIn(client)

            inputYesNo(client)

            client.login().block()
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

        private fun logIn(client: DiscordClient) {
            client.eventDispatcher
                .on(MessageCreateEvent::class.java)
                .subscribe { event: MessageCreateEvent ->
                    event.message.content.ifPresent { c: String? -> println(c) }
                }

            client.eventDispatcher.on(ReadyEvent::class.java)
                .subscribe { event: ReadyEvent ->
                    val self = event.self
                    println(java.lang.String.format("Logged in as %s#%s", self.username, self.discriminator))
                }
        }

        fun randomNumberGenerator(maxNum: Int): String {
            val secureRandom = SecureRandom().nextInt(maxNum)

            return if (secureRandom >= 50) {
                "yes"
            } else {
                "no"
            }
        }
    }
}