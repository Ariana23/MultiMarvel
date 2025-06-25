package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel() as T
    }
}
