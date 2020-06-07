package xyz.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.blog.entity.Result;
import xyz.blog.entity.StatusCode;
import xyz.blog.entity.Type;
import xyz.blog.service.TypeService;

@Api(description = "分类操作接口")
@CrossOrigin
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping()
    public Result findAll() {
        Page<Type> page = typeService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", page);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Type type = typeService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", type);
    }

    @PostMapping
    public Result add(@RequestBody Type type) {
        typeService.add(type);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Type type, @PathVariable String id) {
        type.setId(id);
        typeService.update(type);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        typeService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
