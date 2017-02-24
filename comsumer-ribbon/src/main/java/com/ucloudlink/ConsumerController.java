package com.ucloudlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ucloudlink.domain.RepOutput;
import com.ucloudlink.domain.ResInput;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return restTemplate.getForEntity(
        		"http://ucloudlink-provider-eureka/add?a=10&b=20", String.class).getBody();
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String post() {
    	
    	ResInput res = new ResInput();
    	
    	res.setDesc("123");
    	res.setName("liuwei");
    	
    	RepOutput result = restTemplate.postForEntity("http://ucloudlink-provider-eureka/post", res, RepOutput.class).getBody();
    	
        return result.getResultDesc();
    }

}
