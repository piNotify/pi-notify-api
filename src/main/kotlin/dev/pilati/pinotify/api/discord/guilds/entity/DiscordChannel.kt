package dev.pilati.pinotify.api.discord.guilds.entity

data class DiscordChannel (
    val id: String,
    val name: String,
    val type: Int,
    val position: Int,
)
