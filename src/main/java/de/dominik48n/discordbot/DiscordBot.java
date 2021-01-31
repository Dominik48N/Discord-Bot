package de.dominik48n.discordbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.dominik48n.discordbot.config.BotConfiguration;
import de.dominik48n.discordbot.config.FileConfiguration;
import de.dominik48n.discordbot.logger.Logger;
import javax.security.auth.login.LoginException;
import lombok.Getter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

/**
 * Created by Dominik48N on 31.01.2021
 */
@Getter
public class DiscordBot {

    public static Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();

    private final BotConfiguration botConfiguration;

    public DiscordBot() {
        this.botConfiguration = new FileConfiguration( this.getClass().getClassLoader() ).load();
    }

    /**
     * Start the discord bot
     */
    public void start() {
        final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        Logger.INFO.print( "Start the bot..." );

        builder.setToken( this.botConfiguration.getToken() );
        try {
            builder.build();
        } catch ( final LoginException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the discord bot
     */
    public void shutdown() {
        Logger.INFO.print( "Stops the bot..." );
    }

}
