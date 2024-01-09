

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.JDA
import java.lang.System

class DiscordBot{
    var jda: JDA

    constructor() {
        val token: String? = System.getenv("DISCORD_TOKEN")
        jda = JDABuilder.createDefault(token).build()
    }
}