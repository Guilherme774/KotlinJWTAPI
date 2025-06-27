package com.guilherme774.jwttokens.services.article

import com.guilherme774.jwttokens.domain.article.Article
import com.guilherme774.jwttokens.domain.article.ArticleDTO
import com.guilherme774.jwttokens.repository.ArticleRepository
import org.springframework.stereotype.Service

interface ArticleService {
    fun getAllArticles(): List<ArticleDTO>
    fun getArticleById(id: Int): ArticleDTO
    fun createArticle(article: ArticleDTO): ArticleDTO
    fun updateArticle(article: ArticleDTO): ArticleDTO
    fun deleteArticle(id: Int)
}