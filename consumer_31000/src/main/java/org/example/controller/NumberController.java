package org.example.controller;

import jakarta.annotation.Resource;
import org.example.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/num")
@RefreshScope
public class NumberController {
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/sum")//和
    public CommonResult<Integer> sum(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        List<ServiceInstance> instanceList =discoveryClient.getInstances("provider");
        for(ServiceInstance si : instanceList){
            System.out.println(si.getHost()+"\t"+si.getPort());
        }
        ServiceInstance instance= instanceList.get(0);

        CommonResult<Integer> result = restTemplate.getForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/calculate/add?a="+a+"&"+"b="+b
                , CommonResult.class);
        return result;
    }
    @GetMapping("/difference")//差
    public CommonResult<Integer> difference(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        List<ServiceInstance> instanceList =discoveryClient.getInstances("provider");
        for(ServiceInstance si : instanceList){
            System.out.println(si.getHost()+"\t"+si.getPort());
        }
        ServiceInstance instance= instanceList.get(0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,Integer> map = new LinkedMultiValueMap<>();
        map.add("a",a);
        map.add("b",b);

        HttpEntity<MultiValueMap<String,Integer>> request = new HttpEntity<>(map,headers);

        return restTemplate.postForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/calculate/minus",request
                , CommonResult.class);
    }
    @GetMapping("/product")//积
    public void product(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        List<ServiceInstance> instanceList =discoveryClient.getInstances("provider");
        for(ServiceInstance si : instanceList){
            System.out.println(si.getHost()+"\t"+si.getPort());
        }
        ServiceInstance instance= instanceList.get(0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,Integer> map = new LinkedMultiValueMap<>();
        map.add("a",a);
        map.add("b",b);

        HttpEntity<MultiValueMap<String,Integer>> request = new HttpEntity<>(map,headers);

        restTemplate.put(
                "http://"+instance.getHost()+":"+instance.getPort()+"/calculate/multiply",request);
    }
    @GetMapping("/quotient")//商
    public void quotient(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b) {
        List<ServiceInstance> instanceList =discoveryClient.getInstances("provider");
        for(ServiceInstance si : instanceList){
            System.out.println(si.getHost()+"\t"+si.getPort());
        }
        ServiceInstance instance= instanceList.get(0);

        restTemplate.delete(
                "http://"+instance.getHost()+":"+instance.getPort()+"/calculate/divide?a="+a+"&"+"b="+b
                , CommonResult.class);
    }
}
