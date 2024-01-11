package dev.pilati.pinotify.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.apache.commons.logging.LogFactory
import org.flywaydb.core.Flyway
import DiscordBot
import dev.pilati.pinotify.api.adapter.postgres.MigrationManager

@SpringBootApplication
class PiNotifyApiApplication{
	val discordBot = DiscordBot()

	constructor(migrationManager: MigrationManager) {
		migrationManager.migrate()
	}
}

fun main(args: Array<String>) {
	runApplication<PiNotifyApiApplication>(*args)
}
