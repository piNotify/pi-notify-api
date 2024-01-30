package dev.pilati.pinotify.api.discord.bot.event

import dev.pilati.pinotify.api.discord.bot.command.CommandRegister
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

object GuildReady: ListenerAdapter() {
    private val log: Log = LogFactory.getLog(GuildReady::class.java)


    override fun onGuildReady(event: GuildReadyEvent) {
        log.info("Guild ready: " + event.guild.name)
        CommandRegister().registerAll(event.guild)
    }
}