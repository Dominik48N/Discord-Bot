package de.dominik48n.discordbot.command

import de.dominik48n.discordbot.Running

/**
 * Created by Dominik48N on 31.01.2021
 */
class ShutdownCommand: Command( "shutdown", "stop" ) {

    override fun execute( strings: Array< out String >? ) {
        Running.instance.discordBot.shutdown()
    }

}
