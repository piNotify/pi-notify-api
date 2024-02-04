package dev.pilati.pinotify.api.adapter.postgres.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.UUID
import org.springframework.data.repository.Repository

@Entity
@Table(name = "subscriptions")
class SubscriptionEntity (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: UUID?,

    @Column(name = "channel_id", nullable = false)
    val channelId: UUID,

    @Column(name = "guild_id", nullable = false)
    val guildId: String,

    @Column(name = "text_channel_id", nullable = false)
    val textChannelId: UUID,

    @Column(name = "message_template", nullable = false)
    val messageTemplate: String,

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false, insertable=false, updatable=false)
    val channel: ChannelEntity,
)

interface SubscriptionRepository : Repository<SubscriptionEntity, UUID> {
    fun findByChannelId(channelId: UUID): SubscriptionEntity?
    fun findByGuildId(guildId: String): List<SubscriptionEntity>
    fun save(subscription: SubscriptionEntity): SubscriptionEntity
}
