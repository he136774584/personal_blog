package xyz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.blog.entity.User;
import xyz.blog.mapper.UserMapper;
import xyz.blog.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> findAll(Integer current, Integer size){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, info -> !info.getColumn().equals("password"));
        Page<User> user=userMapper.selectPage(new Page<>(current,size), queryWrapper);
        return user;
    }

    @Override
    public User findById(String id){
        User user1 = userMapper.selectById(id);
        if (user1 != null){
            user1.setPassword(null);
        }
        return user1;
    }

    @Override
    public Boolean add(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1 != null) {
            throw new RuntimeException("用户已存在");
        }

        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        userMapper.insert(user);
        return true;
    }

    @Override
    public void update(User user){
        user.setUpdateTime(new Date());
        if(user.getPassword() != null){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        }
        userMapper.updateById(user);
    }

    @Override
    public void delete(String id){
        userMapper.deleteById(id);
    }

    @Override
    public User login(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        queryWrapper.eq("account", user.getAccount()).eq("password", password);
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1 != null){
            user1.setPassword(null);
        }
        return user1;
    }

}
