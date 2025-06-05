package data

import io.ktor.util.date.*
import utils.HashGenerator

class CharactersService {

    private val charactersRepository = CharactersRepository()

    suspend fun getMarvelCharacters(): List<Character> {
        val timestamp = getTimeMillis()
        val hash = HashGenerator.md5(timestamp.toString() + PrivateKey + PublicKey)

        val charactersResponse = charactersRepository.getMarvelCharacters(timestamp, hash)
        return charactersResponse.toModel()
    }
}
