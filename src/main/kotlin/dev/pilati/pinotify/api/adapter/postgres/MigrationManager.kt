package dev.pilati.pinotify.api.adapter.postgres

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.flywaydb.core.Flyway

@Service
class MigrationManager(
    @Value("\${spring.datasource.url}")
    private val url: String,

    @Value("\${spring.datasource.username}")
    private val user: String,

    @Value("\${spring.datasource.password}")
    private val password: String,
) {
    fun migrate() {
        Flyway.configure().dataSource(url, user, password).load().migrate()
    }
}