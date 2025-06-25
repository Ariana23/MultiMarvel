package ui



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import data.Character
import data.CharactersService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch


class CharactersViewModel(
    private val service: CharactersService = CharactersService()
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    fun fetchCharacters() {
        viewModelScope.launch {
            _characters.value = service.getMarvelCharacters()
        }
    }
}
