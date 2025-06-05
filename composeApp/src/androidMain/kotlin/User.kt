package ui
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card


import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.squareup.picasso.Picasso

@Composable
fun CharacterListScreen() {
    val viewModel: CharactersViewModel = viewModel()
    val characters by viewModel.characters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }



    LazyColumn {
        items(characters) { character ->
            val imageUrl = "https://${character.thumbnail.path}.${character.thumbnail.extension}"

            Spacer(Modifier.height(3.dp))
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    text = "${character.name}: ${character.description}")
                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            Picasso.get().load(imageUrl).into(this)
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )

            }
            }

    }
}

