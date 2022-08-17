package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.dao.GoodsDAO;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsDAO goodsDAO;
    @PostMapping("/save")
    public Result<?> save(@RequestBody Goods goods){
        goodsDAO.insert(goods);
        return Result.success();
    }
    @GetMapping("/search")
    public Result<?> find(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String search){
        Page<Goods> goodsPage=goodsDAO.selectPage(new Page<>(pageNum,pageSize),
                Wrappers.<Goods>lambdaQuery().like(Goods::getMovieName,search));
        return Result.success(goodsPage);
    }
    @DeleteMapping("/delete/{movieId}")
    public Result<?> delete(@PathVariable Long movieId){
        goodsDAO.deleteById(movieId);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> update(@RequestBody Goods goods){
        goodsDAO.updateById(goods);
        return Result.success();
    }
}
