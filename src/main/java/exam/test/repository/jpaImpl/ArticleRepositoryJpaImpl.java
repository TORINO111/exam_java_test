package exam.test.repository.jpaImpl;

import exam.test.core.RepositoryJpaImpl;
import exam.test.entities.Article;
import exam.test.repository.ArticleRepository;
import jakarta.persistence.EntityManager;

public class ArticleRepositoryJpaImpl extends RepositoryJpaImpl<Article> implements ArticleRepository {

    public ArticleRepositoryJpaImpl(EntityManager em) {
        super(Article.class, em);
    }

}
