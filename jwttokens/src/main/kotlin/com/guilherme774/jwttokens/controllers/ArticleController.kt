package com.guilherme774.jwttokens.controllers

import com.guilherme774.jwttokens.domain.article.Article
import com.guilherme774.jwttokens.domain.article.ArticleDTO
import com.guilherme774.jwttokens.services.article.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/article")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping
    fun getArticles(): ResponseEntity<List<ArticleDTO>> {
        return ResponseEntity.ok(articleService.getAllArticles())
    }

    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Int): ResponseEntity<ArticleDTO> {
        return ResponseEntity.ok(articleService.getArticleById(id))
    }

    @PostMapping
    fun createArticle(article: ArticleDTO): ResponseEntity<ArticleDTO> {
        return ResponseEntity(articleService.createArticle(article), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateArticle(article: ArticleDTO): ResponseEntity<ArticleDTO> {
        return ResponseEntity.ok(articleService.updateArticle(article))
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(articleService.deleteArticle(id))
    }
}