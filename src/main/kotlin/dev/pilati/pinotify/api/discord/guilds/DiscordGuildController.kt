package dev.pilati.pinotify.api.discord.guilds

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.http.ResponseEntity
import org.apache.commons.logging.LogFactory
import dev.pilati.pinotify.api.util.GenericResponse

@RestController
@RequestMapping("/discord/guilds")
class DiscordGuildController(
    private val guildService: DiscordGuildService
){
    private val logger = LogFactory.getLog(DiscordGuildService::class.java)

    @RequestMapping("/admin")
    fun getGuildsAdmin(
        @RequestHeader accessToken: String
    ): ResponseEntity<Any> {
        try{

            val guilds = guildService.getGuildsAdmin(accessToken)
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
}