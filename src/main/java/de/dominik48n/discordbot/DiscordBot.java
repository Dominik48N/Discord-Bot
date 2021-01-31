package de.dominik48n.discordbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.dominik48n.discordbot.command.CommandManager;
import de.dominik48n.discordbot.config.BotConfiguration;
import de.dominik48n.discordbot.config.FileConfiguration;
import de.dominik48n.discordbot.logger.Logger;
import javax.security.auth.login.LoginException;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

/**
 * Created by Dominik48N on 31.01.2021
 */
@Getter
public class DiscordBot {

    @Getter private static DiscordBot instance;

    public static Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();

    private final BotConfiguration botConfiguration;
    private final CommandManager commandManager;

    public DiscordBot() {
        instance = this;

        this.botConfiguration = new FileConfiguration( this.getClass().getClassLoader() ).load();
        this.commandManager = new CommandManager();
    }

    /**
     * Start the discord bot
     */
    public void start() {
        Logger.INFO.print( "Start the bot..." );

        this.configureBot();
        this.registerCommands();
    }

    /**
     * Stops the discord bot
     */
    public void shutdown() {
        Logger.INFO.print( "Stops the bot..." );

        this.commandManager.stop();
    }

    /**
     * Register all commands
     */
    private void registerCommands() {

    }

    /**
     * Configures the bot
     */
    private void configureBot() {
        final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        builder.setToken( this.botConfiguration.getToken() );
        builder.setStatus( OnlineStatus.ONLINE );
        builder.setActivity( Activity.playing( this.botConfiguration.getPlayingGame() ) );

        try {
            builder.build();
        } catch ( final LoginException e ) {
            e.printStackTrace();
        }
    }

}
