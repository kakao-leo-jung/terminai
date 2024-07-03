package org.devjk

import com.google.gson.JsonParser
import java.net.HttpURLConnection
import java.net.URL

class OpenAiClient(
    private val apiUrl: String = "https://api.openai.com/v1/chat/completions"
) {

    fun call(query: String) {
        val jsonInputString = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [
                    {
                        "role": "system",
                        "content": "You are a robotic assistant. answer with korean"
                    },
                    {
                        "role": "user",
                        "content": "$query"
                    }
                ]
            }
        """.trimIndent()

        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Authorization", "Bearer ${System.getenv("OPENAI_API_KEY")}")
            connection.doOutput = true

            connection.outputStream.use { os ->
                val input: ByteArray = jsonInputString.toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            }

            connection.inputStream.use { inputStream ->
                val response = inputStream.bufferedReader().use { it.readText() }
                val result = JsonParser.parseString(response).asJsonObject
                val answer = result.getAsJsonArray("choices")[0].asJsonObject
                    .getAsJsonObject("message")
                    .get("content").asString
                println(answer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
    }

}