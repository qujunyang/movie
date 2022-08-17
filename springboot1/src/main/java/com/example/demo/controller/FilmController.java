package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.dao.FilmDAO;
import com.example.demo.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmDAO filmDAO;
    @GetMapping("/search")
    public Result<?> find(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String search){
        Page<Film> FilmPage=filmDAO.selectPage(new Page<>(pageNum,pageSize),
                Wrappers.<Film>lambdaQuery().like(Film::getFilmName,search));
        return Result.success(FilmPage);
    }
    @DeleteMapping("/delete/{filmId}")
    public Result<?> delete(@PathVariable Long filmId){
        filmDAO.deleteById(filmId);
        return Result.success();
    }
}
