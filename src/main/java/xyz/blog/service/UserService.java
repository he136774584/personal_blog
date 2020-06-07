package xyz.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.blog.entity.User;

public interface UserService {

    Page<User> findAll();

    User findById(String id);

    Boolean add(User user);

    void update(User user);

    void delete(String id);

    User login(User user);

}
