package xyz.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.blog.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
