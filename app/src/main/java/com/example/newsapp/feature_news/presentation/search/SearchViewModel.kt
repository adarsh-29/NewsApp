package com.example.newsapp.feature_news.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.feature_news.domain.model.Article
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel
@Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(SearchUiState())

    val uiState =
        _uiState.asStateFlow()

    private val queryFlow =
        MutableStateFlow("")

    val pagingFlow:
            Flow<PagingData<Article>> =

        queryFlow
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->

                if (query.isBlank()) {
                    flowOf(PagingData.empty())
                } else {
                    useCases.searchNews(query)
                }
            }
            .cachedIn(viewModelScope)

    fun onQueryChange(
        query: String
    ) {

        _uiState.value =
            _uiState.value.copy(
                query = query
            )

        queryFlow.value =
            query
    }
}