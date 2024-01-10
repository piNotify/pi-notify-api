package dev.pilati.pinotify.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.apache.commons.logging.LogFactory
import DiscordBot

@SpringBootApplication
class PiNotifyApiApplication

fun main(args: Array<String>) {
	runApplication<PiNotifyApiApplication>(*args)
	DiscordBot()
}
