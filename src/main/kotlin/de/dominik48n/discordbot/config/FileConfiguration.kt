package de.dominik48n.discordbot.config

import de.dominik48n.discordbot.document.Document
import java.io.File
import java.io.IOException
import java.nio.file.Files

/**
 * Created by Dominik48N on 02.03.2021
 */
class FileConfiguration(private val classLoader: ClassLoader) {

    private val document = this.mkdirs(this.classLoader)

    fun load(): BotConfiguration {
        return BotConfiguration(this.document, this.document.getStringValue("bot_token"), this.document.getStringValue("playing_game"))
    }

    private fun mkdirs(classLoader: ClassLoader): Document {
        val file = File("configuration/", "config.json")

        if (!file.exists()) {
            file.parentFile.mkdirs()

            try {
                val inputStream = classLoader.getResourceAsStream("config.json")

                if (inputStream != null) Files.copy(inputStream, file.toPath())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return Document.read(file)
    }

}
