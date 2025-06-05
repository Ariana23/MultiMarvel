package utils.data

import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse(val data: DataContainer)

@Serializable
data class DataContainer(val results: List<Character>)

@Serializable
data class Character(val name: String, val description: String)

