package dev.pilati.pinotify.api.discord.command

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

class CommandRegister {
    fun registerAll(guild: Guild) {
        guild.updateCommands()
            .addCommands(
                commandAddChannel()
            ).queue()
    }

    fun commandAddChannel(): SlashCommandData {
        return Commands.slash("add-channel", "Add Channel to be notified")
    }
}