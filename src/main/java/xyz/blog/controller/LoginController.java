package xyz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.blog.entity.Result;
import xyz.blog.entity.StatusCode;
import xyz.blog.entity.User;
import xyz.blog.service.UserService;
import xyz.blog.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/common")
public class LoginController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        user = userService.login(user);
        if(user==null){
            return new Result(false, StatusCode.LOGINERROR, "登陆失败");
        }
        String token = jwtUtil.createJWT(user.getId()+"", user.getNickname(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", "Bearer " + token);
        map.put("user", user);
        return new Result(true, StatusCode.OK, "登陆成功", map);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        user.setRole("1");
        Boolean b = userService.add(user);
        if(b){
            return new Result(true, StatusCode.OK,"注册成功") ;
        }else{
            return new Result(false, StatusCode.ERROR,"注册失败") ;
        }
    }

}
