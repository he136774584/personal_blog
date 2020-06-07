package xyz.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.blog.entity.Type;

public interface TypeService {
    Page<Type> findAll();

    Type findById(String id);

    Boolean add(Type type);

    void update(Type type);

    void delete(String id);
}
