package com.example.newsapp.feature_news.data.mapper

import com.example.newsapp.feature_news.data.remote.dto.ArticleDto
import com.example.newsapp.feature_news.domain.model.Article

fun ArticleDto.toDomain(): Article {

    return Article(
        sourceName = source?.name.orEmpty(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty(),
        isBookmarked = false
    )
}