package dev.pilati.pinotify.api.adapter.postgres.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import java.util.UUID
import org.springframework.data.repository.Repository

@Entity
@Table(name = "channels")
class ChannelEntity (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: UUID?,

    @Column(name = "channel_id", nullable = false)
    val channelId: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "url", nullable = false)
    val url: String,
)

interface ChannelRepository : Repository<ChannelEntity, UUID> {
    fun findByChannelId(channelId: String): ChannelEntity?
    fun save(channel: ChannelEntity): ChannelEntity
}
