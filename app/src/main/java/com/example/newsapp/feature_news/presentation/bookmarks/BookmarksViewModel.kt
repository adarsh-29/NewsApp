package com.example.newsapp.feature_news.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel
@Inject constructor(
    private val useCases:
    NewsUseCases
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            BookmarksUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        observeBookmarks()
    }

    private fun observeBookmarks() {

        viewModelScope.launch {

            useCases
                .getBookmarks()
                .collect { list ->

                    _uiState.value =
                        _uiState.value.copy(
                            articles = list
                        )
                }
        }
    }

    fun toggleBookmark(
        url: String
    ) {

        viewModelScope.launch {

            useCases.toggleBookmark(url)
        }
    }
}