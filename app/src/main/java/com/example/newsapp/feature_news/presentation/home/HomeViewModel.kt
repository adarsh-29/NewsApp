package com.example.newsapp.feature_news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    useCases: NewsUseCases
) : ViewModel() {

    val pagingFlow =
        useCases
            .getTopHeadlines()
            .cachedIn(viewModelScope)

    private val _uiState =
        MutableStateFlow(HomeUiState())

    val uiState:
            StateFlow<HomeUiState> =
        _uiState.asStateFlow()
}