package dev.pilati.pinotify.api.discord.event

import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.interactions.commands.Command
import org.apache.commons.logging.LogFactory
import dev.pilati.pinotify.api.discord.command.CommandRegister

object GuildReady: ListenerAdapter() {
    val log = LogFactory.getLog(GuildReady::class.java)


    override fun onGuildReady(event: GuildReadyEvent) {
        log.info("Guild ready: " + event.guild.name)
        CommandRegister().registerAll(event.guild)
    }
}