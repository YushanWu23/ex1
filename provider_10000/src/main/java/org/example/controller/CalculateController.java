package org.example.controller;

import org.example.entity.CommonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    @GetMapping("/add")
    public CommonResult<Integer> add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer code = 200;
        String message = "计算和(provider_10000)";
        try {
            result.setResult(a+b);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        return result;
    }
    @PostMapping("/minus")
    public CommonResult<Integer> minus(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer code = 200;
        String message = "计算差(provider_10000)";
        try {
            result.setResult(a-b);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        return result;
    }
    @PutMapping("/multiply")
    public CommonResult<Integer> multiply(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer code = 200;
        String message = "计算积(provider_10000)";
        try {
            result.setResult(a*b);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        System.out.println(result);
        return result;
    }
    @DeleteMapping("/divide")
    public CommonResult<Integer> divide(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer code = 200;
        String message = "计算商(provider_10000)";
        try {
            result.setResult(a/b);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        System.out.println(result);
        return result;
    }
}
