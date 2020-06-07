package xyz.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.blog.entity.Article;

public interface ArticleService {
    Page<Article> findAll();

    Article findById(String id);

    Boolean add(Article article);

    void update(Article article);

    void delete(String id);
}
