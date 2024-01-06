package dev.pilati.pinotify.api.domain.model;

import java.util.UUID

data class Subscription (
    val id: UUID?,
    val channelId: UUID,
    val guildId: UUID,
    val textChannelId: UUID,
    val messageTemplate: String,
)