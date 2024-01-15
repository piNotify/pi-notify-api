package dev.pilati.pinotify.api.discord

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import org.apache.commons.logging.LogFactory
import dev.pilati.pinotify.api.discord.DiscordService
import DiscordLogin

@RestController
@RequestMapping("/discord")
class DiscordController(
    private val discordService: DiscordService
) {

    @GetMapping("/login")
    fun login(
        @RequestParam code: String
    ): DiscordLogin? {
        return discordService.login(code)
    }
}