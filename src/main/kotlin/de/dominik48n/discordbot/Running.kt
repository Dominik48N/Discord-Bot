package de.dominik48n.discordbot

/**
 * Created by Dominik48N on 31.01.2021
 */
fun main( args: Array< String > ) {
    val discordBot: DiscordBot = DiscordBot()

    discordBot.start()

    Runtime.getRuntime().addShutdownHook( object: Thread() {
        override fun run() {
            discordBot.shutdown()
        }
    } )
}
