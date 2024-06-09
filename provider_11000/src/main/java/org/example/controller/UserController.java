package org.example.controller;

import org.example.entity.CommonResult;
import org.example.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDao")
public class UserController {
    @PostMapping("/newUser")
    public CommonResult<User> getUserById(@RequestBody User user) throws InterruptedException{
        CommonResult<User> result = new CommonResult<User>();
        Integer code = 200;
        String message = "success";
        User result1;
        try {
            result1 = user;
        } catch (Exception e) {
            code = 500;
            message = "failed";
            result1 = null;
        }
        Thread.sleep(3000);
        result.setMessage(message);
        result.setCode(code);
        result.setResult(result1);
        return result;
    }
}
