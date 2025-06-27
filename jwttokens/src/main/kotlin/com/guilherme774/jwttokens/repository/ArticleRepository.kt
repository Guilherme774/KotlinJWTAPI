package com.guilherme774.jwttokens.repository

import com.guilherme774.jwttokens.domain.article.Article
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
interface ArticleRepository: CrudRepository<Article, Int> {
    @Query("SELECT a FROM Article As a")
    fun getAllArticles(): List<Article>
}