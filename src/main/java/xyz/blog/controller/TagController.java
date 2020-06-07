package xyz.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.blog.entity.Result;
import xyz.blog.entity.StatusCode;
import xyz.blog.entity.Tag;
import xyz.blog.service.TagService;

@Api(description = "标签操作接口")
@CrossOrigin
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping()
    public Result findAll() {
        Page<Tag> page = tagService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", page);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Tag tag = tagService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", tag);
    }

    @PostMapping
    public Result add(@RequestBody Tag tag) {
        tagService.add(tag);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Tag tag, @PathVariable String id) {
        tag.setId(id);
        tagService.update(tag);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        tagService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
