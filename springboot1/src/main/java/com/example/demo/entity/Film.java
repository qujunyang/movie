package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
//film_id`, `film_name`, `film_location`, `film_kind`, `film_language`, `film_info`, `film_link`, `film_img`
@TableName("film")
public class Film {
    @TableId(value = "film_id",type = IdType.AUTO)
    private Integer filmId;
    private String filmName;
    private String filmLocation;
    private String filmKind;
    private String filmLanguage;
    private String filmInfo;
    private String filmLink;
    private String filmImg;
}
