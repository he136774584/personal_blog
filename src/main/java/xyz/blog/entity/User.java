package xyz.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="b_user")
public class User {

    @TableId(value="id")
    private String id;
    private String nickname;
    private String account;
    private String password;
    private String role;
    private Date createTime;
    private Date updateTime;
}
