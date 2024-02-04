package dev.pilati.pinotify.api.discord

import java.math.BigInteger

object DiscordConstants{

    @Suppress("MagicNumber")
    final val ADMINISTRATOR_PERMISSION = BigInteger.valueOf(0x8),
    
    const val CHANNEL_TYPE_GUILD_TEXT = 0,
    
    const val CHANNEL_TYPE_ANNOUNCEMENT_THREAD = 5
}
