package dev.pilati.pinotify.api.discord.bot.command

import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

object AddChannelCommand: ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(!event.name.equals("add-channel")) {
            return
        }

        val modal = createAddChannelModal()
        event.replyModal(modal).queue()
    }

    private fun createAddChannelModal(): Modal {
        val url: TextInput = TextInput.create("url", "Channel URL", TextInputStyle.SHORT)
            .setPlaceholder("Channel URL")
            .setRequired(true)
            .build()
        
        val channel: EntitySelectMenu = EntitySelectMenu.create("channel", EntitySelectMenu.SelectTarget.CHANNEL)
            .setPlaceholder("Select a channel")
            .setChannelTypes(ChannelType.TEXT)
            .setRequiredRange(1, 1)
            .build()
        
        val textTemplate: TextInput = TextInput.create("template", "Message Template\nTeste", TextInputStyle.PARAGRAPH)
            .setPlaceholder("{link}")
            .setValue("{link}")
            .setRequired(true)
            .build()

        return Modal.create("addChannel", "Add Channel")
            .addComponents(
                ActionRow.of(url),
                ActionRow.of(channel),
                ActionRow.of(textTemplate)
            ).build()
            
    }

}
