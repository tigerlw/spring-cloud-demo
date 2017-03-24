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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ucloudlink.domain.RepOutput;
import com.ucloudlink.domain.ResInput;
import com.ucloudlink.domain.SwaggerInput;
import com.ucloudlink.utils.SerializeTool;

import io.swagger.models.Swagger;
import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

@RestController
public class ProviderEurekaController {

	private final Logger logger = Logger.getLogger(getClass());
	private final AtomicLong counter = new AtomicLong();
	private Single<String> single;

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
	private DocumentationCache documentationCache;
    
    @Autowired
    private ServiceModelToSwagger2Mapper mapper;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private JsonSerializer jsonSerializer;
	
	@RequestMapping(value="/addDoc", method=RequestMethod.GET)
	public String addDocs()
	{
		Documentation documentation = documentationCache.documentationByGroup("test");
		
		Swagger swagger = mapper.mapDocumentation(documentation);
		
		swagger.setBasePath("/api");
		
		SwaggerInput swaggerInput = new SwaggerInput();
		
		swaggerInput.setGroupName("test");
		swaggerInput.setSwagger(jsonSerializer.toJson(swagger).value());
		
		String result = restTemplate.postForEntity("http://api-gateway/addDoc",swaggerInput, String.class).getBody();
		return result;
	}
  
    @RequestMapping(value="api/hello", method=RequestMethod.GET)
    public ProviderEurekaGreeting helloGreeting(
    		@RequestParam(value="name", defaultValue="World") String name, 
    		@RequestParam(value="age", defaultValue="10000") int age) {
    	
        return new ProviderEurekaGreeting(counter.incrementAndGet(), age, name);
    }
    
    @RequestMapping(value="/hello2", method=RequestMethod.GET)
    public String helloT()
    {
    	try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "hello";
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
    	
    	result.setResultDesc("123");
    	
		return result;
    	
    }
    
    @RequestMapping(value = "/asyn" ,method = RequestMethod.GET)
    public  DeferredResult<String> asyn()
    {
    	DeferredResult<String> deferredResult = new DeferredResult<String>();
    	
    	new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				deferredResult.setResult("liuwei");
			}
    		
    	}).start();
    	
		return deferredResult;
    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/publishT")
    public Single<String> publishSubjectT()
    {
    	PublishSubject<String> stringPublishSubject = PublishSubject.create();
    	
    	new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stringPublishSubject.onNext("liuwei");

			}
    		
    	}).start();
    	
    	return stringPublishSubject.toSingle();
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
						
					/*	try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
						observer.onNext("===================================================");
						observer.onCompleted();
					}
				});
		
		
		
		//observableString.timeout(60000, TimeUnit.SECONDS);

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
    
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @RequestMapping(method = RequestMethod.GET, value = "/failBack")
    public String failBack(@RequestParam String param)
    {
    	if("fail".equals(param))
    	{
    		throw new RuntimeException("call dependency service fail.");
    	}
    	
    	
    	List<String> severList = discoveryClient.getServices();
    	
    	StringBuilder build = new StringBuilder();
    	
        for(String item:severList)
        {
        	List<ServiceInstance> services = discoveryClient.getInstances(item);
        	
        	for(ServiceInstance service : services)
        	{
        		build.append("http://"+service.getHost()+":"+service.getPort()+"/swagger-ui.html;");
        	}
        }
        
        String result = build.toString();
    	
    	
    	
    	return result;
    }
    
    public String fallbackMethod(String param)
    {
    	return "==================================== fail!";
    }
    
   // @PostMapping
	//@ResponseBody
    public String postMapping()
    {
    	return "post";
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


