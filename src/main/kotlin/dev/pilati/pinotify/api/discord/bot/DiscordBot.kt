package dev.pilati.pinotify.api.discord.bot

import dev.pilati.pinotify.api.discord.bot.command.AddChannelCommand
import dev.pilati.pinotify.api.discord.bot.event.GuildReady
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class DiscordBot {
    private var jda: JDA

    init {
        val token: String? = System.getenv("DISCORD_TOKEN")
        jda = JDABuilder.createDefault(token)
            .addEventListeners(AddChannelCommand)
            .addEventListeners(GuildReady)
            .build()
    }
}