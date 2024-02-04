package dev.pilati.pinotify.api.discord.auth

@Suppress("ConstructorParameterNaming")
data class DiscordLogin (
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
)
