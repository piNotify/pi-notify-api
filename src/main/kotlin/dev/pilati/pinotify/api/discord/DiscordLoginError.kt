package dev.pilati.pinotify.api.discord

data class DiscordLoginError (
    val error: String,
    val error_description: String
)