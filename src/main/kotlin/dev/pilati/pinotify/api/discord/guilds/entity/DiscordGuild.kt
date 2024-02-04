package dev.pilati.pinotify.api.discord.guilds.entity

data class DiscordGuild (
    val id: String,
    val name: String,
    val icon: String?,
    val botIsPresent: Boolean = false
)
