package dev.pilati.pinotify.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PiNotifyApiApplication

fun main(args: Array<String>) {
	runApplication<PiNotifyApiApplication>(*args)
	println("Hello World!")
}
