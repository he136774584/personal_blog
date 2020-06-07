package xyz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blog.entity.Type;
import xyz.blog.mapper.TypeMapper;
import xyz.blog.service.TypeService;

import java.util.Date;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Page<Type> findAll() {
        Page<Type> types = typeMapper.selectPage(new Page<>(1, 10), null);
        return types;
    }

    @Override
    public Type findById(String id) {
        return typeMapper.selectById(id);
    }

    @Override
    public Boolean add(Type type) {
        if (hasType(type)) {
            throw new RuntimeException("分类已存在");
        }
        Date date = new Date();
        type.setCreateTime(date);
        type.setUpdateTime(date);
        typeMapper.insert(type);
        return true;
    }

    @Override
    public void update(Type type) {
        if (hasType(type)) {
            throw new RuntimeException("分类已存在");
        }
        type.setUpdateTime(new Date());
        typeMapper.updateById(type);
    }

    @Override
    public void delete(String id) {
        typeMapper.deleteById(id);
    }

    private boolean hasType(Type type) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", type.getName());
        Type type1 = typeMapper.selectOne(queryWrapper);
        if (type1 == null) {
            return false;
        }
        return true;
    }
}
