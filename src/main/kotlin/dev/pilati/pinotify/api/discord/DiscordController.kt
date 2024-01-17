package dev.pilati.pinotify.api.discord

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.HttpClientErrorException
import org.springframework.http.ResponseEntity
import org.apache.commons.logging.LogFactory
import dev.pilati.pinotify.api.discord.DiscordService
import dev.pilati.pinotify.api.util.GenericResponse

@RestController
@RequestMapping("/discord")
class DiscordController(
    private val discordService: DiscordService
) {
    private val logger = LogFactory.getLog(DiscordController::class.java)

    @GetMapping("/login")
    fun login(
        @RequestParam code: String
    ): ResponseEntity<Any> {
        try{
            
            val discordLogin: DiscordLogin? = discordService.login(code)!!
            return ResponseEntity.ok(discordLogin)

        } catch(e: Exception) {
            logger.error("Error on DiscordLooginController.login", e)

            return ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error on Discord login"
                )
            )
        }
    }
}