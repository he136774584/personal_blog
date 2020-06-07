package xyz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blog.entity.Tag;
import xyz.blog.mapper.TagMapper;
import xyz.blog.service.TagService;

import java.util.Date;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Page<Tag> findAll() {
        Page<Tag> tags = tagMapper.selectPage(new Page<>(1, 10), null);
        return tags;
    }

    @Override
    public Tag findById(String id) {
        return tagMapper.selectById(id);
    }

    @Override
    public Boolean add(Tag tag) {
        if (hasTag(tag)) {
            throw new RuntimeException("标签已存在");
        }
        Date date = new Date();
        tag.setCreateTime(date);
        tag.setUpdateTime(date);
        tagMapper.insert(tag);
        return true;
    }

    @Override
    public void update(Tag tag) {
        if (hasTag(tag)) {
            throw new RuntimeException("标签已存在");
        }
        tag.setUpdateTime(new Date());
        tagMapper.updateById(tag);
    }

    @Override
    public void delete(String id) {
        tagMapper.deleteById(id);
    }

    private boolean hasTag(Tag tag) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", tag.getName());
        Tag tag1 = tagMapper.selectOne(queryWrapper);
        if (tag1 == null) {
            return false;
        }
        return true;
    }
}
