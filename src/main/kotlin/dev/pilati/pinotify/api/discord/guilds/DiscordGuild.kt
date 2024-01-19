package dev.pilati.pinotify.api.discord.guilds

data class DiscordGuild (
    val id: String,
    val name: String,
    val icon: String?,
    val owner: Boolean,
    val permissions: String,
    var botIsPresent: Boolean = false
)