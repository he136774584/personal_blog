package xyz.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.blog.entity.Article;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
}
