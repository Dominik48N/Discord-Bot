package de.dominik48n.discordbot.command

import de.dominik48n.discordbot.bootstrap.Running

/**
 * Created by Dominik48N on 31.01.2021
 */
class ShutdownCommand : Command {

    override fun execute(args: Array<String>) {
        Running.instance.discordBot.stop()
    }

}
