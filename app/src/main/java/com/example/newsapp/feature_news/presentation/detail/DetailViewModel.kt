package com.example.newsapp.feature_news.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@HiltViewModel
class DetailViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: NewsUseCases
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            DetailUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        val encoded =
            savedStateHandle
                .get<String>("url")

        encoded?.let {

            val decoded =
                URLDecoder.decode(
                    it,
                    StandardCharsets.UTF_8.toString()
                )

            load(decoded)
        }
    }

    private fun load(
        url: String
    ) {

        viewModelScope.launch {

            val article =
                useCases.getArticle(url)

            _uiState.value =
                _uiState.value.copy(
                    article = article
                )
        }
    }

    fun toggleBookmark() {

        val url =
            _uiState.value
                .article
                ?.url
                ?: return

        viewModelScope.launch {

            useCases.toggleBookmark(url)

            load(url)
        }
    }
}