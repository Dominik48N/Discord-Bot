package de.dominik48n.discordbot.command

import de.dominik48n.discordbot.bootstrap.Running

class ShutdownCommand : Command {

    override fun execute(args: Array<String>) {
        Running.instance.discordBot.stop()
    }

}
