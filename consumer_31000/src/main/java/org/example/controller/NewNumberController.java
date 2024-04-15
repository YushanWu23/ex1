package org.example.controller;
import jakarta.annotation.Resource;
import org.example.NumberFeignClient;
import org.example.entity.CommonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newNum")
public class NewNumberController {
    @Resource
    private NumberFeignClient numberFeignClient;

    @GetMapping("/sum")//和
    public CommonResult<Integer> sum(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return numberFeignClient.add(a,b);
    }
    @GetMapping("/difference")//差
    public CommonResult<Integer> difference(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        return numberFeignClient.minus(a,b);
    }
    @GetMapping("/product")//积
    public void product(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        numberFeignClient.multiply(a,b);
    }
    @GetMapping("/quotient")//商
    public void quotient(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        numberFeignClient.divide(a,b);
    }
}

