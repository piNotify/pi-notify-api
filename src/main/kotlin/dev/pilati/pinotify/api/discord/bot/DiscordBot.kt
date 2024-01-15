

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.JDA
import java.lang.System
import dev.pilati.pinotify.api.discord.command.AddChannelCommand
import dev.pilati.pinotify.api.discord.event.GuildReady

class DiscordBot{
    var jda: JDA

    constructor() {
        val token: String? = System.getenv("DISCORD_TOKEN")
        jda = JDABuilder.createDefault(token)
            .addEventListeners(AddChannelCommand)
            .addEventListeners(GuildReady)
            .build()
    }
}