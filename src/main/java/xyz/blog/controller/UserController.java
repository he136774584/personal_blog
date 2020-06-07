package xyz.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.blog.entity.Result;
import xyz.blog.entity.StatusCode;
import xyz.blog.entity.User;
import xyz.blog.service.UserService;

@Api(description = "用户操作接口")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户列表", notes="获取用户列表")
    @GetMapping()
    public Result findAll(){
        Page<User> page = userService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",page) ;
    }

    @ApiOperation(value = "用户查询", notes="获取用户By id")
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        User user = userService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功",user) ;
    }

    @PostMapping
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result(true, StatusCode.OK,"添加成功") ;
    }

    @PutMapping(value="/{id}")
    public Result update(@RequestBody User user,@PathVariable String id){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        userService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
