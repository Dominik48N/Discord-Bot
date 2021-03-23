package de.dominik48n.discordbot

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.dominik48n.discordbot.command.CommandManager
import de.dominik48n.discordbot.command.ShutdownCommand
import de.dominik48n.discordbot.config.BotConfiguration
import de.dominik48n.discordbot.config.FileConfiguration
import de.dominik48n.discordbot.logger.Logger
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import javax.security.auth.login.LoginException
import kotlin.system.exitProcess

class DiscordBot {

    companion object {
        val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    private var botConfiguration: BotConfiguration = FileConfiguration(this.javaClass.classLoader).load()
    private var commandManager: CommandManager = CommandManager()

    private lateinit var shardManager: ShardManager

    /**
     * Start the discord bot
     */
    fun start() {
        Logger.INFO.print("Start the bot...")

        this.configureBot()
        this.registerCommands()
    }

    /**
     * Stops the discord bot
     */
    fun stop() {
        Logger.INFO.print("Stops the bot...")

        this.shardManager.setStatus(OnlineStatus.OFFLINE)
        this.shardManager.shutdown()

        this.commandManager.stop()

        exitProcess(0)
    }

    /**
     * Register all commands
     */
    private fun registerCommands() {
        this.commandManager.addCommand("shutdown", ShutdownCommand())
    }

    /**
     * Configures the bot
     */
    private fun configureBot() {
        val defaultSharedManagerBuilder = DefaultShardManagerBuilder()

        defaultSharedManagerBuilder.setToken(this.botConfiguration.token)
        defaultSharedManagerBuilder.setStatus(OnlineStatus.ONLINE)
        defaultSharedManagerBuilder.setActivity(Activity.playing(this.botConfiguration.playingGame))

        try {
            this.shardManager = defaultSharedManagerBuilder.build()
        } catch (e: LoginException) {
            e.printStackTrace()
        }
    }

}
