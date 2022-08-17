package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res=userDAO.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,user.getUserName())
                .eq(User::getUserPwd,user.getUserPwd()));
        if (res==null){
            return Result.error("-1","用户名或密码错误");
        }else {
            return Result.success();
        }
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res=userDAO.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,user.getUserName()));

        if (res!=null){
            return Result.error("-1","用户名已存在");
        }else {
            userDAO.insert(user);
            return Result.success();
        }
    }

}
