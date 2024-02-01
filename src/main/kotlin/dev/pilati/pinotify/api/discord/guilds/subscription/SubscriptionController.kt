package dev.pilati.pinotify.api.discord.guilds.subscription

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import dev.pilati.pinotify.api.domain.model.Subscription

@RestController
@RequestMapping("/discord/guild/{guildId}")
class SubscriptionController(
    private val subscriptionService: SubscriptionService
){
    @GetMapping("/subscriptions")
    fun getSubscriptions(
        @PathVariable guildId: String
    ): List<Subscription> {
        return subscriptionService.getSubscriptions(guildId)
    }

    @PostMapping("/subscription")
    fun addSubscription(
        @PathVariable guildId: String
    ): List<Subscription> {
        return subscriptionService.getSubscriptions(guildId)
    }
}