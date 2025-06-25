package ui

import android.util.Log
import org.multimarvel.R

//import androidx.compose.animation.core.R

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.ui.res.painterResource

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CharacterListScreen(viewModel: CharactersViewModel) {
    val characters by viewModel.characters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    LazyColumn {
        items(characters) { character ->

            val imageUrl = character.thumbnail.path.replace("http://", "https://") +
                    "/portrait_xlarge." + character.thumbnail.extension

            Spacer(Modifier.height(3.dp))
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "${character.name}: ${character.description}"
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .error(R.drawable.meme)
                        .build(),
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}
