package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("snap")
@Data
public class Goods {
    @TableId(value = "movie_id",type = IdType.AUTO)
    private Integer movieId;
    private String movieName;
    private String movieKind;
    private String movieLink;
    private String movieMoment;
}
