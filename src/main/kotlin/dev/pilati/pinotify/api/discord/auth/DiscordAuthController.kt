package dev.pilati.pinotify.api.discord.auth

import dev.pilati.pinotify.api.util.GenericResponse
import org.apache.commons.logging.LogFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/discord/auth")
class DiscordAuthController(
    private val authService: DiscordService
) {
    private val logger = LogFactory.getLog(DiscordAuthController::class.java)

    @GetMapping("/login")
    fun login(
        @RequestParam code: String
    ): ResponseEntity<Any> {
        return try{

            val discordLogin: DiscordLogin? = authService.login(code)!!
            ResponseEntity.ok(discordLogin)

        } catch(e: Exception) {
            logger.error("Error on DiscordLoginController.login", e)

            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error on Discord login"
                )
            )
        }
    }

    @GetMapping("/refresh")
    fun refresh(
        @RequestParam refreshToken: String
    ): ResponseEntity<Any> {
        return try{

            val discordLogin: DiscordLogin? = authService.refresh(refreshToken)!!
            ResponseEntity.ok(discordLogin)

        } catch(e: Exception) {
            logger.error("Error on DiscordLoginController.refresh", e)

            ResponseEntity.badRequest().body(
                GenericResponse(
                    error = true,
                    message = "Error on Discord login"
                )
            )
        }
    }
}
