package dev.pilati.pinotify.api.discord.guilds

import dev.pilati.pinotify.api.discord.guilds.exception.GuildNotAdminException
import dev.pilati.pinotify.api.discord.guilds.exception.GuildNotPresentException
import dev.pilati.pinotify.api.util.GenericResponse
import org.apache.commons.logging.LogFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/discord")
class DiscordGuildController(
    private val guildService: DiscordGuildService
){
    private val logger = LogFactory.getLog(DiscordGuildController::class.java)

    @GetMapping("/guilds/admin")
    fun getGuildsAdmin(
        @RequestHeader accessToken: String
    ): ResponseEntity<Any> {
        return try{
            ResponseEntity.ok(
                guildService.getUserGuildsAdmin(accessToken)
            )
        } catch(e: Exception) {
            logger.error("Error on DiscordGuildController.getGuilds", e)

            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error getting guilds on Discord"
                )
            )
        }
    }

    @GetMapping("/guild/{guildId}")
    fun getGuild(
        @RequestHeader accessToken: String,
        @PathVariable guildId: String
    ): ResponseEntity<Any> {
        return try{
            ResponseEntity.ok(
                guildService.getUserGuild(guildId, accessToken)
            )
        } catch(e: GuildNotAdminException) {
            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    error_code = "guild-not-admin",
                    message = "Error getting guild on Discord"
                )
            )
        } catch(e: GuildNotPresentException) {
            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    error_code = "guild-not-present",
                    message = "Error getting guild on Discord"
                )
            )
        } catch(e: Exception) {
            logger.error("Error on DiscordGuildController.getGuild", e)

            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error getting guild on Discord"
                )
            )
        }
    }

    @GetMapping("/guild/{guildId}/channels")
    fun getGuildChannels(
        @RequestHeader accessToken: String,
        @PathVariable guildId: String
    ): ResponseEntity<Any> {
        return try{
            ResponseEntity.ok(
                guildService.getGuildTextChannels(guildId, accessToken)
            )
        } catch(e: Exception) {
            logger.error("Error on DiscordGuildController.getGuildChannels", e)

            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error getting guild channels on Discord"
                )
            )
        }
    }
}
