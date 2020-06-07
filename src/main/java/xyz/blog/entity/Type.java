package xyz.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="b_type")
public class Type {
    @TableId(value="id")
    private String id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
