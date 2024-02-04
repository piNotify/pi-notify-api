package dev.pilati.pinotify.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import dev.pilati.pinotify.api.discord.bot.DiscordBot
import dev.pilati.pinotify.api.adapter.postgres.MigrationManager

@SpringBootApplication

class PiNotifyApiApplication {
    val discordBot = DiscordBot()

    constructor(migrationManager: MigrationManager) {
        migrationManager.migrate()
    }
}

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PiNotifyApiApplication>(*args)
}
