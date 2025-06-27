package com.guilherme774.jwttokens.domain.article

data class ArticleDTO (
    var id: Int = 0,
    var user_id: Int,
    var title: String,
    var text: String
)