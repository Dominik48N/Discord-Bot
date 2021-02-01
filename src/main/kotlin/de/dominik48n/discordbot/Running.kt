package de.dominik48n.discordbot

/**
 * Created by Dominik48N on 31.01.2021
 */
fun main( args: Array< String > ) {
    val discordBot: DiscordBot = DiscordBot()

    discordBot.start()

    Running( discordBot )

    Runtime.getRuntime().addShutdownHook( object: Thread() {
        override fun run() {
            discordBot.shutdown()
        }
    } )
}

class Running ( val discordBot: DiscordBot ){

    companion object {
        @JvmStatic lateinit var instance: Running
    }

    init {
        instance = this
    }

}
