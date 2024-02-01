package dev.pilati.pinotify.api.discord.guilds.subscription.entity

data class AddSubscription(
    val url: String,
    val textChannelId: String,
    val messageTemplate: String,
)