package exam.test.services;

import exam.test.repository.ArticleRepository;
import exam.test.entities.Article;

import java.util.List;

import exam.test.core.Item;


public class ArticleService implements Item<Article>{
    ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void create(Article element) {
        articleRepository.insert(element);
    }

    @Override
    public void update(Article element) {
        articleRepository.insert(element);
    }

    @Override
    public List<Article> listAll() {
        return articleRepository.selectAll();
    }

    
}
