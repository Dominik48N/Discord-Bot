package de.dominik48n.discordbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.dominik48n.discordbot.config.BotConfiguration;
import de.dominik48n.discordbot.config.FileConfiguration;
import de.dominik48n.discordbot.logger.Logger;
import lombok.Getter;

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
        Logger.INFO.print( "Start the bot..." );
    }

    /**
     * Stops the discord bot
     */
    public void shutdown() {
        Logger.INFO.print( "Stops the bot..." );
    }

}
