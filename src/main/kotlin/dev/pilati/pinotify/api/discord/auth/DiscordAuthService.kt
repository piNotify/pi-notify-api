package dev.pilati.pinotify.api.discord.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClient

@Service
class DiscordAuthService(
    @Value("\${discord.api-url}")
    private val apiUrl: String,

    @Value("\${discord.client-id}")
    private val clientId: String,

    @Value("\${discord.client-secret}")
    private val clientSecret: String,

    @Value("\${discord.redirect-uri}")
    private val redirectUri: String,

    restClientBuilder: RestClient.Builder
) {
    private val restClient: RestClient = restClientBuilder.baseUrl(apiUrl).build()

    fun login(code: String): DiscordLogin? {
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        map.add("grant_type", "authorization_code")
        map.add("code", code)
        map.add("redirect_uri", redirectUri)
        map.add("client_id", clientId)
        map.add("client_secret", clientSecret)

        return restClient.post()
            .uri("/oauth2/token")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .body(map)
            .retrieve()
            .body(DiscordLogin::class.java)
    }

    fun refresh(refreshToken: String): DiscordLogin? {
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        map.add("grant_type", "refresh_token")
        map.add("refresh_token", refreshToken)
        map.add("client_id", clientId)
        map.add("client_secret", clientSecret)

        return restClient.post()
            .uri("/oauth2/token")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .body(map)
            .retrieve()
            .body(DiscordLogin::class.java)
    }
}
