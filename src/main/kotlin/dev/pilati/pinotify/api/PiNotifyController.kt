package dev.pilati.pinotify.api

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/")
class PiNotifyController{
    @RequestMapping("/")
    fun hello(): String {
        val hello = "Hello, world!"
        return hello
    }
}
