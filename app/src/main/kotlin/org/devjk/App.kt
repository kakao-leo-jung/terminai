package org.devjk

class App(
    private val openAiClient: OpenAiClient = OpenAiClient()
) {

    fun run() {
        setup()
        start()
    }

    private fun setup() {
        // todo
    }

    private fun start() {
        val logo = """
 _                           _                _ 
| |                         (_)              (_)
| |_   ___  _ __  _ __ ___   _  _ __    __ _  _ 
| __| / _ \| '__|| '_ ` _ \ | || '_ \  / _` || |
| |_ |  __/| |   | | | | | || || | | || (_| || |
 \__| \___||_|   |_| |_| |_||_||_| |_| \__,_||_|
        """.trimIndent()
        println(logo)
        println("[terminai] Ask what anythings what you want to know")


        print(" > ")
        var query: String? = ""
        do {
            query = readLine()
        } while (query.isNullOrBlank())
        openAiClient.call(query)

    }
}

fun main() {
    App().run()
}