package org.example;

import jakarta.annotation.Resource;
import org.example.entity.CommonResult;
import org.example.entity.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("signIn")
    public CommonResult<User> newUser(@RequestParam(value = "id") String id,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "password") String password){
        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = instanceList.get(0);
        URI uri = serviceInstance.getUri();
        User user = new User();
        user.setId(id);
        user.setPassWord(password);
        user.setName(name+"由第第个consumer处理");
        return restTemplate.postForObject(uri+"/userDao/newUser",user,CommonResult.class);

    }
}
