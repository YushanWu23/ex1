package org.example;

import org.example.entity.CommonResult;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("Provider")
//@LoadBalancerClient
@LoadBalancerClient(name = "Provider",configuration =NumberLoadBalancer.class)
public interface NumberFeignClient {
    @GetMapping("/calculate/add")
    public CommonResult<Integer> add(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);

    @PostMapping("/calculate/minus")
    public CommonResult<Integer> minus(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);

    @PutMapping("/calculate/multiply")
    public CommonResult<Integer> multiply(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);

    @DeleteMapping("/calculate/divide")
    public CommonResult<Integer> divide(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);

}
