package org.example.controller;

import jakarta.annotation.Resource;
import org.example.entity.CommonResult;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/getCartById/{userId}")
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId) {
        //通过服务提供者名（provider-server）获取Eureka Server上的服务注册列表
        List<ServiceInstance> instanceList =discoveryClient.getInstances("provider");
        for(ServiceInstance si : instanceList){
            System.out.println(si.getHost()+"\t"+si.getPort());
        }
        ServiceInstance instance= instanceList.get(0);

        CommonResult<User> result = restTemplate.getForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/user/getUserById/"+userId.toString()
                , CommonResult.class);
        return result;
    }
}
