package com.camp;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rx.Observable;
import rx.Single;
import rx.subjects.PublishSubject;

@FeignClient("camp-provider-eureka")
public interface ConsumerClient {

	@RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
	
	@RequestMapping(method = RequestMethod.GET, value = "/single")
	Single<String> single();
	
	@RequestMapping(method = RequestMethod.GET, value = "/publish")
    public Single<String> publishSubject();

}
