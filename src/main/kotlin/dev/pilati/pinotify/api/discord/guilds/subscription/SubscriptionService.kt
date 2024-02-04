package dev.pilati.pinotify.api.discord.guilds.subscription

import dev.pilati.pinotify.api.adapter.postgres.entity.SubscriptionRepository
import dev.pilati.pinotify.api.adapter.postgres.entity.converter.toModel
import dev.pilati.pinotify.api.domain.model.Subscription
import org.springframework.stereotype.Service

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository
){
    fun getSubscriptions(guildId: String): List<Subscription> {
        return subscriptionRepository
            .findByGuildId(guildId)
            .stream()
            .map { subscriptionEntity -> subscriptionEntity.toModel() }
            .toList()
    }
}
