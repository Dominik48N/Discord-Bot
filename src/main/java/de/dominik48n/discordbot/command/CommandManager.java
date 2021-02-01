package de.dominik48n.discordbot.command;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dominik48N on 30.01.2021
 */
public class CommandManager {

    private final List< Command > commands;
    private final Thread thread;

    public CommandManager() {
        this.commands = Lists.newArrayList();

        this.thread = new Thread( () -> {
            final Scanner scanner = new Scanner( System.in );
            String input;

            while ( ( input = scanner.nextLine() ) != null ) {
                final String[] inputArguments = input.split( " " ), arguments = new String[ inputArguments.length - 1 ];

                assert inputArguments.length > 0;

                System.arraycopy( inputArguments, 1, arguments, 0, arguments.length );

                for ( final Command command : this.commands ) {
                    if ( !command.getAliases().contains( inputArguments[ 0 ].toLowerCase() ) ) continue;

                    command.execute( arguments );
                }
            }

        } );

        this.thread.start();
    }

    /**
     * Add new commands in the cloud system
     *
     * @param commands the array with all new commands
     */
    public void addCommands( final Command... commands ) {
        this.commands.addAll( Arrays.asList( commands ) );
    }

    /**
     * Shuts down the command thread
     */
    public void stop() {
        this.thread.stop();
    }

}
