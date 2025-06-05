package data
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.serialization.json.Json



internal val PublicKey = "3795a204a355b051999bbe06eb143182"
internal val PrivateKey = "2916e685924c4802527a7a046c31cfad5e366891"

class CharactersRepository {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun getMarvelCharacters(timestamp: Long, md5: String): CharactersResponse {
        val response = httpClient.get {
            url("https://gateway.marvel.com/v1/public/characters")
            parameter("apikey", PublicKey)
            parameter("ts", timestamp)
            parameter("hash", md5)
        }.body<CharactersResponse>()

        return response
    }
}
