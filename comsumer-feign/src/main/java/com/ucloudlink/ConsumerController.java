package com.ucloudlink;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.subjects.PublishSubject;

@RestController
public class ConsumerController {

	@Autowired
	ConsumerClient client;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add() {
		return client.add(20, 20);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/single")
	public String single() {

		Single<String> singleStr = client.single();

		//String result = "";

		singleStr.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				System.out.println("Observable completed");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Oh no! Something wrong happened!");
			}

			@Override
			public void onNext(String message) {

				System.out.println(message);
			}
		});

		return "123";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/publish")
    public String publishSubject()
    {
		Single<String> publish = client.publishSubject();
		
		
		publish.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				System.out.println("Observable completed");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Oh no! Something wrong happened!"+e.toString());
			}

			@Override
			public void onNext(String message) {

				System.out.println(message);
			}
		});
		
		System.out.println("success===============");
		
		return "publish";
    }
}
