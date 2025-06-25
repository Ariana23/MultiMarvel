package org.multimarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ui.CharacterListScreen
import ui.CharactersViewModel
import ui.CharactersViewModelFactory
import utils.HashGenerator
import utils.data.initDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val driver = AndroidSqliteDriver(MarvelDatabase.Schema, this, "marvel.db")
        initDatabase(driver)

        val factory = CharactersViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)[CharactersViewModel::class.java]

        setContent {
            CharacterListScreen(viewModel = viewModel)
        }
    }
}

