package de.dominik48n.discordbot.logger

enum class Logger(private val prefix: String) {

    INFO( "[INFO]" ),
    ERROR( "[ERROR]" ),
    WARNING( "[WARNING]" );

    fun print(message: String) {
        println(this.prefix + ' ' + message)
    }

}
