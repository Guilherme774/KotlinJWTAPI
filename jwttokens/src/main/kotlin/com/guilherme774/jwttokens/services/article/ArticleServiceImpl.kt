package com.guilherme774.jwttokens.services.article

import com.guilherme774.jwttokens.domain.article.ArticleDTO
import com.guilherme774.jwttokens.repository.ArticleRepository
import com.guilherme774.jwttokens.utils.exception.ArticleException
import com.guilherme774.jwttokens.utils.mapper.ArticleMapper
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val articleMapper: ArticleMapper
): ArticleService {
    override fun getAllArticles(): List<ArticleDTO> {
        val articles = articleRepository.getAllArticles()

        if(articles.isEmpty())
            throw ArticleException("Oh no, we have any articles yet!")

        return articles.map {
            articleMapper.fromEntity(it)
        }
    }

    override fun getArticleById(id: Int): ArticleDTO {
        val selectedArticle = articleRepository.findById(id)
        val article = selectedArticle.orElseThrow { ArticleException("Oh no, that article haven't been found") }

        return articleMapper.fromEntity(article)
    }

    override fun createArticle(article: ArticleDTO): ArticleDTO {
        if(article.id != 0)
            throw ArticleException("That ID is not valid to create an Article")

        val articleToAdd = articleMapper.toEntity(article)
        articleRepository.save(articleToAdd)

        return articleMapper.fromEntity(articleToAdd)
    }

    override fun updateArticle(article: ArticleDTO): ArticleDTO {
        val articleExists = articleRepository.existsById(article.id)

        if(!articleExists)
            throw ArticleException("Oh no, that article haven't been found")

        val articleToUpdate = articleMapper.toEntity(article)
        articleRepository.save(articleToUpdate)

        return article
    }

    override fun deleteArticle(id: Int) {
        val articleExists = articleRepository.existsById(id)

        if(!articleExists)
            throw ArticleException("Oh no, that article haven't been found")

        articleRepository.deleteById(id)
    }
}