package xyz.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.blog.entity.Tag;

public interface TagService {
    Page<Tag> findAll();

    Tag findById(String id);

    Boolean add(Tag tag);

    void update(Tag tag);

    void delete(String id);
}
