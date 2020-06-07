package xyz.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.blog.entity.Article;
import xyz.blog.entity.Result;
import xyz.blog.entity.StatusCode;
import xyz.blog.service.ArticleService;

@Api(description = "文章操作接口")
@CrossOrigin
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping()
    public Result findAll() {
        Page<Article> article = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Article tag = articleService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", tag);
    }

    @PostMapping
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.update(article);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        articleService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
