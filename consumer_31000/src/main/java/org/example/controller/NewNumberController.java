package org.example.controller;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import jakarta.annotation.Resource;
import org.example.NumberFeignClient;
import org.example.entity.CommonResult;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/newNum")
public class NewNumberController {
    @Resource
    private NumberFeignClient numberFeignClient;

    @GetMapping("/sum")//和
    @CircuitBreaker(name = "circuitBreakerA",fallbackMethod = "fallbackSum")
    public CommonResult<Integer> sum(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return numberFeignClient.add(a,b);
    }
    public CommonResult<Integer> fallbackSum(Integer a, Integer b, Throwable T){
        CommonResult<Integer> result = new CommonResult<>();
        result.setCode(404);
        result.setResult(null);
        result.setMessage("A服务降级");
        System.out.println("A服务降级");
        return result;
    }
    @GetMapping("/sum2")//和
    @Bulkhead(name = "bulkheadC",fallbackMethod = "fallbackSum2")
    public CommonResult<Integer> sum2(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return numberFeignClient.add(a,b);
    }
    public CommonResult<Integer> fallbackSum2(Integer a, Integer b, Throwable T){
        CommonResult<Integer> result = new CommonResult<>();
        result.setCode(404);
        result.setResult(null);
        result.setMessage("C发生隔离");
        System.out.println("C发生隔离");
        return result;
    }
    @GetMapping("/sum3")//和
    @Bulkhead(name = "rateLimiterD",fallbackMethod = "fallbackSum3")
    public CommonResult<Integer> sum3(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return numberFeignClient.add(a,b);
    }
    public CommonResult<Integer> fallbackSum3(Integer a, Integer b, Throwable T){
        CommonResult<Integer> result = new CommonResult<>();
        result.setCode(404);
        result.setResult(null);
        result.setMessage("D发生限流");
        System.out.println("D发生限流");
        return result;
    }
    @GetMapping("/difference")//差
    @CircuitBreaker(name = "circuitBreakerB",fallbackMethod = "fallbackDifference")
    public CommonResult<Integer> difference(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        return numberFeignClient.minus(a,b);
    }
    public CommonResult<Integer> fallbackDifference(Integer a, Integer b, Throwable T){
        CommonResult<Integer> result = new CommonResult<>();
        result.setCode(404);
        result.setResult(null);
        result.setMessage("B服务降级");
        System.out.println("B服务降级");
        return result;
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

