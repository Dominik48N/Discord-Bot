package de.dominik48n.discordbot.logger

/**
 * Created by Dominik48N on 31.01.2021
 */
enum class Logger( private val prefix: String ) {

    INFO( "[INFO]" ),
    ERROR( "[ERROR] " ),
    WARNING( "[WARNING] " );

    fun print( message: String ) {
        println( this.prefix + " " + message )
    }

}
