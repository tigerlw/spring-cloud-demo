package com.ucloudlink;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucloudlink.domain.RepOutput;
import com.ucloudlink.domain.ResInput;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.subjects.PublishSubject;

@RestController
class ProviderEurekaController {

	private final Logger logger = Logger.getLogger(getClass());
	private final AtomicLong counter = new AtomicLong();
	private Single<String> single;

    @Autowired
    private DiscoveryClient discoveryClient;
  
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public ProviderEurekaGreeting helloGreeting(
    		@RequestParam(value="name", defaultValue="World") String name, 
    		@RequestParam(value="age", defaultValue="10000") int age) {
 
        return new ProviderEurekaGreeting(counter.incrementAndGet(), age, name);
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
 
        Integer r = a + b;
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
 
    	return this.discoveryClient.getInstances(applicationName);
    }
    
    @RequestMapping(value = "/post" ,method = RequestMethod.POST)
    public @ResponseBody RepOutput postMethod(@RequestBody ResInput res)
    {
    	RepOutput result = new RepOutput();
    	
    	result.setResultDesc(res.getDesc());
    	
		return result;
    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/publish")
	public Single<String> publishSubject() {
    	
		//Observable(10, TimeUnit.SECONDS);
		Observable<String> observableString = Observable
				.create(new Observable.OnSubscribe<String>() {
					public void call(Subscriber<? super String> observer) {
						/*for (int i = 0; i < 1000; i++) {
							observer.onNext(String.valueOf(i));
							// System.out.println("Send some:"+i);
						}*/
						
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						observer.onNext("===================================================");
						observer.onCompleted();
					}
				});
		
		
		
		observableString.timeout(60000, TimeUnit.SECONDS);

		// stringPublishSubject.onNext("123");

		return observableString.toSingle();
	}
    
    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public Single<String> single() {
    	
    	Single<String> result = Single.fromCallable(new Callable<String>(){

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				
			
				return "single value";
			}

		
    		
    	});
    	
    	
    	return result;
    }
    
    private String hello()
    {
    	/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	return "single value";
    }

}


