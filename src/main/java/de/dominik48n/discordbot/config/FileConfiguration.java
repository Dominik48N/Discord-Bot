package de.dominik48n.discordbot.config;

import de.dominik48n.discordbot.document.Document;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Dominik48N on 31.01.2021
 */
public class FileConfiguration {

    private final Document document;

    public FileConfiguration( final ClassLoader classLoader ) {
        this.document = this.mkdirs( classLoader );
    }

    public BotConfiguration load() {
        return new BotConfiguration( this.document, this.document.getString( "bot_token" ) );
    }

    private Document mkdirs( final ClassLoader classLoader ) {
        final File file = new File( "configuration/", "config.json" );

        if ( !file.exists() ) {
            file.getParentFile().mkdirs();

            try ( final InputStream inputStream = classLoader.getResourceAsStream( "config.json" ) ) {
                if ( inputStream != null ) Files.copy( inputStream, file.toPath() );
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
        }

        return Document.loadDocument( file );
    }

}
