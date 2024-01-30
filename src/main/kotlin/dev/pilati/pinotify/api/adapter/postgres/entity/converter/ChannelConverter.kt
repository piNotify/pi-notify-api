package dev.pilati.pinotify.api.adapter.postgres.entity.converter

import dev.pilati.pinotify.api.domain.model.Channel
import dev.pilati.pinotify.api.adapter.postgres.entity.ChannelEntity

fun Channel.toEntity(): ChannelEntity {
    return ChannelEntity(
        id = this.id,
        channelId = this.channelId,
        name = this.name,
        url = this.url,
    )
}

fun ChannelEntity.toModel(): Channel {
    return Channel(
        id = this.id,
        channelId = this.channelId,
        name = this.name,
        url = this.url,
    )
}