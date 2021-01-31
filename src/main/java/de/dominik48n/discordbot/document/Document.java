package de.dominik48n.discordbot.document;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.dominik48n.discordbot.DiscordBot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

/**
 * Created by Dominik48N on 31.01.2021
 */
public class Document {

    private static final JsonParser PARSER = new JsonParser();

    @Getter private JsonObject dataCatcher;
    @Getter private String name;
    @Getter private File file;

    public Document() {
        this.dataCatcher = new JsonObject();
    }

    public Document( final JsonObject jsonObject ) {
        this.dataCatcher = jsonObject;
    }

    public Document( final String name ) {
        this.name = name;
        this.dataCatcher = new JsonObject();
    }

    public Document( final String name, final JsonObject source ) {
        this.name = name;
        this.dataCatcher = source;
    }

    public Document( final File file, final JsonObject jsonObject ) {
        this.file = file;
        this.dataCatcher = jsonObject;
    }

    public Document( final String key, final String value ) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    @Deprecated
    public Document append( final String key, final Object value ) {
        if ( value == null ) return this;
        if ( value instanceof Document ) {
            this.append( key, ( Document ) value );
            return this;
        }

        this.dataCatcher.add( key, DiscordBot.GSON.toJsonTree( value ) );
        return this;
    }

    public Document remove( final String key ) {
        this.dataCatcher.remove( key );
        return this;
    }

    public Set< String > keySet() {
        final Set< String > set = Sets.newHashSet();

        for ( final Map.Entry< String, JsonElement > entry : this.dataCatcher.entrySet() ) {
            set.add( entry.getKey() );
        }

        return set;
    }

    public < T > T get( final String key, final Class< T > clazz ) {
        return this.dataCatcher.has( key ) ? DiscordBot.GSON.fromJson( this.dataCatcher.get( key ), clazz ) : null;
    }

    public String getString( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get( key ).getAsString() : null;
    }

    public int getInt( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get( key ).getAsInt() : 0;
    }

    public long getLong( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get(key).getAsLong() : 0L;
    }

    public double getDouble( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get( key) .getAsDouble() : 0D;
    }

    public boolean getBoolean( final String key ) {
        return this.dataCatcher.has( key ) && this.dataCatcher.get( key ).getAsBoolean();
    }

    public float getFloat( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get(key).getAsFloat() : 0F;
    }

    public short getShort( final String key ) {
        return this.dataCatcher.has( key ) ? this.dataCatcher.get( key ).getAsShort() : 0;
    }

    public Document getDocument( final String key ) {
        return this.dataCatcher.has( key ) ? new Document( this.dataCatcher.get( key ).getAsJsonObject() ) : null;
    }

    public boolean save( final File file ) {
        if ( file == null ) return false;
        if ( file.exists() ) file.delete();

        return this.save( file.getPath() );
    }

    public boolean save( final Path path ) {
        try ( final OutputStreamWriter writer = new OutputStreamWriter( Files.newOutputStream( path ), Charsets.UTF_8 ) ) {
            DiscordBot.GSON.toJson( this.dataCatcher, writer );
            return true;
        } catch ( final IOException e ) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save( final String path ) {
        return this.save( Paths.get( path ) );
    }

    public String convertToJson() {
        return DiscordBot.GSON.toJson( this.dataCatcher );
    }

    public static Document loadDocument( final Path path ) {

        try ( final InputStreamReader reader = new InputStreamReader( Files.newInputStream( path ), Charsets.UTF_8 );
              final BufferedReader bufferedReader = new BufferedReader( reader ) ) {
            return new Document( PARSER.parse( bufferedReader ).getAsJsonObject() );
        } catch ( final IOException e ) {
            e.printStackTrace();
            return new Document();
        }
    }

    public static Document loadDocument( final File file ) {
        return loadDocument( file.toPath() );
    }

    public static Document load( final JsonObject jsonObject ) {
        return new Document( jsonObject );
    }

    public boolean isEmpty() {
        return this.dataCatcher.size() == 0;
    }

    @Override
    public String toString() {
        return convertToJsonString();
    }

    public String convertToJsonString() {
        return this.dataCatcher.toString();
    }

}
