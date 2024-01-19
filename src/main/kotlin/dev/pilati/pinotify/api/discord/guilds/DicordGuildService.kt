package dev.pilati.pinotify.api.discord.guilds

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient
import org.springframework.core.ParameterizedTypeReference
import java.util.Arrays
import java.math.BigInteger

@Service
class DiscordGuildService(
    @Value("\${discord.api-url}")
    private val apiUrl: String,

    private val restClientBuilder: RestClient.Builder
) {
    private val restClient: RestClient = restClientBuilder.baseUrl(apiUrl).build()

    private final val DISCORD_ADMINISTRATOR_PERMISSION: BigInteger = BigInteger.valueOf(0x8)

    fun getGuildsAdmin(accessToken: String): List<DiscordGuild> {
        
        val guilds: List<DiscordGuild> = restClient.get()
            .uri("/users/@me/guilds")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .body(object : ParameterizedTypeReference<List<DiscordGuild>>() {})!!

        return guilds.stream()
            .filter({guild: DiscordGuild -> isGuildAdmin(guild)})
            .toList()
    }

    private fun isGuildAdmin(guild: DiscordGuild): Boolean {
        return guild.owner == true 
            || guild.permissions.toBigInteger().and(DISCORD_ADMINISTRATOR_PERMISSION).compareTo(BigInteger.ZERO) > 0
    }
}