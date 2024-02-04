package dev.pilati.pinotify.api.discord.guilds.entity

data class UserDiscordGuild (
    val id: String,
    val name: String,
    val icon: String?,
    val owner: Boolean,
    val permissions: String,
    val botIsPresent: Boolean = false
)
