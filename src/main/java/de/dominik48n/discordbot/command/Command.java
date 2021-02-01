package de.dominik48n.discordbot.command;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;

/**
 * Created by Dominik48N on 30.01.2021
 */
public abstract class Command {

    @Getter private final List< String > aliases;

    public Command( final String command ) {
        this.aliases = Lists.newArrayList( command );
    }

    public Command( final String command, final String... aliases ) {
        this.aliases = Lists.newArrayList( aliases );

        this.aliases.add( command );
    }

    public abstract void execute( final String[] strings );

}
