package com.example.newsapp.feature_news.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    savedStateHandle:
    SavedStateHandle,

    private val useCases:
    NewsUseCases
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            DetailUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        val url =
            savedStateHandle
                .get<String>("url")

        if (url != null) {
            load(url)
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

    fun toggleBookmark(
        url: String
    ) {

        viewModelScope.launch {

            useCases.toggleBookmark(url)
        }
    }
}