package de.dominik48n.discordbot.config

import de.dominik48n.discordbot.document.Document

data class BotConfiguration(val document: Document, val token: String, val playingGame: String)
