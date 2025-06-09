package ui



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import data.Character
import data.CharactersService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CharactersViewModel : ViewModel() {
    private val charactersService = CharactersService()
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    fun fetchCharacters() {
        viewModelScope.launch {
            _characters.value = charactersService.getMarvelCharacters()
        }
    }
}