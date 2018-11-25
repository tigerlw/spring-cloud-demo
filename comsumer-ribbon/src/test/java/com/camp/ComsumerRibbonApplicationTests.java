package com.camp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.camp.main.ComsumerRibbonApplication;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComsumerRibbonApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ComsumerRibbonApplicationTests {

	   @BeforeClass
	    public static void startEureka() {
		   // 先运行 register-eureka 和 provider-eureka
	    }

	    @AfterClass
	    public static void closeEureka() {
	    }

	    @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate testRestTemplate;
	    
	    @Test
	    public void campAddTest() throws InterruptedException {
	    	Thread.sleep(3000);    // registration has to take place...

	        ResponseEntity<String> response = this.testRestTemplate.getForEntity(
	        		"http://localhost:" + this.port + "/add", String.class);

	        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	        then(response.getBody()).contains("30");
	    }

}
