package dev.pilati.pinotify.api.discord.guilds

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient
import org.springframework.core.ParameterizedTypeReference
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.Dispatchers

@Service
class DiscordGuildService(
    @Value("\${discord.api-url}")
    private val apiUrl: String,

    @Value("\${discord.client-id}")
    private val clientId: String,

    @Value("\${discord.token}")
    private val token: String,

    private val restClientBuilder: RestClient.Builder
) {
    private val restClient: RestClient = restClientBuilder.baseUrl(apiUrl).build()

    private final val DISCORD_ADMINISTRATOR_PERMISSION: BigInteger = BigInteger.valueOf(0x8)


    fun getGuildsAdmin(accessToken: String): List<DiscordGuild> {
        
        var guilds: List<DiscordGuild> = restClient.get()
            .uri("/users/@me/guilds")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .body(object : ParameterizedTypeReference<List<DiscordGuild>>() {})!!

        
        guilds = guilds.stream()
            .filter({guild: DiscordGuild -> isGuildAdmin(guild)})
            .toList()

        return populateBotIsPresent(guilds)
    }

    private fun isGuildAdmin(guild: DiscordGuild): Boolean {
        return guild.owner == true 
            || guild.permissions.toBigInteger().and(DISCORD_ADMINISTRATOR_PERMISSION).compareTo(BigInteger.ZERO) > 0
    }

    private fun populateBotIsPresent(guilds: List<DiscordGuild>): List<DiscordGuild>{
        runBlocking{
            coroutineScope {
                
                val deferreds: List<Deferred<Unit>> = guilds.map { guild -> 
                    async((Dispatchers.Default)) {
                        guild.botIsPresent = checkBotIsPresent(guild)
                    }
                }
                
                deferreds.awaitAll()
            }
        }

        return guilds
    }

    private fun checkBotIsPresent(guild: DiscordGuild): Boolean {
        try{
            val response = restClient.get()
                .uri("/guilds/${guild.id}/members/" + clientId)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bot " + token)
                .retrieve()
                .body(String::class.java)
           
                return response != null
        } catch(e: Exception) {
            return false
        }
    }
}