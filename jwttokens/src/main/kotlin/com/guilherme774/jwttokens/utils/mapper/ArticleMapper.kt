package com.guilherme774.jwttokens.utils.mapper

import com.guilherme774.jwttokens.domain.article.Article
import com.guilherme774.jwttokens.domain.article.ArticleDTO
import org.springframework.stereotype.Component

@Component
class ArticleMapper: Mapper<ArticleDTO, Article> {
    override fun fromEntity(entity: Article): ArticleDTO {
        return ArticleDTO(
            entity.id,
            user_id = entity.user_id,
            title = entity.title,
            text = entity.text
        )
    }

    override fun toEntity(domain: ArticleDTO): Article {
        return Article(
            domain.id,
            user_id = domain.user_id,
            title = domain.title,
            text = domain.text
        )
    }
}