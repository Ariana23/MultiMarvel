package ui
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CharacterListScreen() {
    val viewModel: CharactersViewModel = viewModel()
    val characters by viewModel.characters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    LazyColumn {
        items(characters) { character ->
            Text(text = "${character.name}: ${character.description}")
        }
    }
}

