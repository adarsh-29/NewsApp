package com.example.newsapp.feature_news.data.mapper

import com.example.newsapp.feature_news.data.local.entity.ArticleEntity
import com.example.newsapp.feature_news.data.remote.dto.ArticleDto
import com.example.newsapp.feature_news.domain.model.Article

fun ArticleDto.toEntity(): ArticleEntity {

    return ArticleEntity(
        url = url.orEmpty(),
        sourceName = source?.name.orEmpty(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

fun ArticleEntity.toDomain(): Article {

    return Article(
        sourceName = sourceName,
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content,
        isBookmarked = isBookmarked
    )
}