package xyz.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="b_article")
public class Article {
    @TableId(value="id")
    private String id;
    private String title;
    private String introduce;
    private String content;
    private String type;
    private String cover_img;
    private Date createTime;
    private Date updateTime;
}
