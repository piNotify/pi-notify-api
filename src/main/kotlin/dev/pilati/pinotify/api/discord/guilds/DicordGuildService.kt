package dev.pilati.pinotify.api.discord.guilds

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient
import org.springframework.core.ParameterizedTypeReference
import org.apache.commons.logging.LogFactory
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

    private fun getAllUserGuilds(accessToken: String): List<UserDiscordGuild> {
        return restClient.get()
            .uri("/users/@me/guilds")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .body(object : ParameterizedTypeReference<List<UserDiscordGuild>>() {})!!
    }

    private fun isGuildAdmin(guild: UserDiscordGuild): Boolean {
        return guild.owner == true 
            || guild.permissions.toBigInteger().and(DISCORD_ADMINISTRATOR_PERMISSION).compareTo(BigInteger.ZERO) > 0
    }

    private fun asyncPopulateBotIsPresent(guilds: List<UserDiscordGuild>): List<UserDiscordGuild>{
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

    fun getUserGuildsAdmin(accessToken: String): List<UserDiscordGuild> {
        var guilds = getAllUserGuilds(accessToken)
        
        guilds = guilds.stream()
            .filter({guild: UserDiscordGuild -> isGuildAdmin(guild)})
            .toList()

        return asyncPopulateBotIsPresent(guilds)
    }

    private fun checkBotIsPresent(guild: UserDiscordGuild): Boolean {
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

    fun getUserGuild(guildId: String, accessToken: String): UserDiscordGuild{
        val guild = getAllUserGuilds(accessToken).stream()
            .filter({guild: UserDiscordGuild -> guild.id == guildId})
            .findFirst()
            .orElseThrow({GuildNotFoundException()})
        
        if(!isGuildAdmin(guild)){
            throw GuildNotAdminException()
        }

        guild.botIsPresent = checkBotIsPresent(guild)
        if(!guild.botIsPresent){
            throw GuildNotPresentException()
        }

        return guild
    }
}