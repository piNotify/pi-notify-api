package dev.pilati.pinotify.api.adapter.postgres.entity.converter;

import dev.pilati.pinotify.api.adapter.postgres.entity.SubscriptionEntity
import dev.pilati.pinotify.api.domain.model.Subscription

fun Subscription.toEntity(): SubscriptionEntity {
    return SubscriptionEntity(
        id = this.id,
        channelId = this.channelId,
        guildId = this.guildId,
        textChannelId = this.textChannelId,
        messageTemplate = this.messageTemplate,
    )
}

fun SubscriptionEntity.toModel(): Subscription {
    return Subscription(
        id = this.id,
        channelId = this.channelId,
        guildId = this.guildId,
        textChannelId = this.textChannelId,
        messageTemplate = this.messageTemplate,
    )
}