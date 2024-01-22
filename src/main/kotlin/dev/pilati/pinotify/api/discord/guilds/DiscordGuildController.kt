package dev.pilati.pinotify.api.discord.guilds

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.apache.commons.logging.LogFactory
import dev.pilati.pinotify.api.util.GenericResponse
import kotlin.error

@RestController
@RequestMapping("/discord")
class DiscordGuildController(
    private val guildService: DiscordGuildService
){
    private val logger = LogFactory.getLog(DiscordGuildService::class.java)

    @RequestMapping("/guilds/admin")
    fun getGuildsAdmin(
        @RequestHeader accessToken: String
    ): ResponseEntity<Any> {
        try{

            val guilds = guildService.getUserGuildsAdmin(accessToken)
            return ResponseEntity.ok(guilds)

        } catch(e: Exception) {
            logger.error("Error on DiscordGuildController.getGuilds", e)

            return ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error getting guilds on Discord"
                )
            )
        }
    }

    @RequestMapping("/guild/{guildId}")
    fun getGuild(
        @RequestHeader accessToken: String,
        @PathVariable guildId: String
    ): ResponseEntity<Any> {
        try{

            val guild = guildService.getUserGuild(guildId, accessToken)
            return ResponseEntity.ok(guild)

        } catch(e: GuildNotAdminException) {
            return ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    error_code = "guild-not-admin",
                    message = "Error getting guild on Discord"
                )
            )
        } catch(e: GuildNotPresentException) {
            return ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    error_code = "guild-not-present",
                    message = "Error getting guild on Discord"
                )
            )
        } catch(e: Exception) {
            logger.error("Error on DiscordGuildController.getGuild", e)

            return ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error getting guild on Discord"
                )
            )
        }
    }
}