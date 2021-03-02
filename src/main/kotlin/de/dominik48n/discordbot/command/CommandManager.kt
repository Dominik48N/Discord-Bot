package de.dominik48n.discordbot.command

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class CommandManager() {

    private val commands: HashMap<String, Command> = HashMap()
    private var running: Boolean = false

    init {
        Thread({
            val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

            this.running = true

            while (this.running) {
                val fullString: Array<String> = bufferedReader.readLine().split(' ').toTypedArray();
                val command = this.commands[fullString[0]]

                if (command == null) {
                    println("The command does not exists!")
                    continue;
                }

                command.execute(fullString.copyOfRange(1, fullString.size))
            }
        }, "discordbot-command").start()
    }

    fun addCommand(name: String, command: Command) {
        this.commands[name] = command
    }

    fun stop() {
        this.running = false
    }

}
