package xyz.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blog.entity.Article;
import xyz.blog.mapper.ArticleMapper;
import xyz.blog.service.ArticleService;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Page<Article> findAll() {
        Page<Article> article = articleMapper.selectPage(new Page<>(1, 10), null);
        return article;
    }

    @Override
    public Article findById(String id) {
        return articleMapper.selectById(id);
    }

    @Override
    public Boolean add(Article article) {
        Date date = new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        articleMapper.insert(article);
        return true;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(new Date());
        articleMapper.updateById(article);
    }

    @Override
    public void delete(String id) {
        articleMapper.deleteById(id);
    }
}
