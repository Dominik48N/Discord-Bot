package de.dominik48n.discordbot.bootstrap

import de.dominik48n.discordbot.DiscordBot

fun main(args: Array<String>) {
    Thread.currentThread().name = "discord-bot-core"
    val discordBot = DiscordBot()

    discordBot.start()

    Running(discordBot)

    Runtime.getRuntime().addShutdownHook(Thread({
        discordBot.stop()
    }, "discord-bot-shutdown"))
}

class Running(val discordBot: DiscordBot) {

    companion object {
        lateinit var instance: Running
    }

    init {
        instance = this
    }

}
