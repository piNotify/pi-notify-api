package dev.pilati.pinotifica.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PiNotificaApiApplication

fun main(args: Array<String>) {
	runApplication<PiNotificaApiApplication>(*args)
}
