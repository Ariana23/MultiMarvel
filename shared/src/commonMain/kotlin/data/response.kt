package data

import kotlinx.serialization.Serializable


@Serializable
data class CharactersResponse(
    val data: DataContainer
) {
    fun toModel(): List<Character> {
        return this.data.results.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail
            )
        }
    }
}

@Serializable
data class DataContainer(
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun toUrl(): String = "$path.$extension"
}
