package dev.pilati.pinotify.api.domain.model

import java.util.UUID

data class Channel (
    val id: UUID?,
    val channelId: String,
    val name: String,
    val url: String,
)