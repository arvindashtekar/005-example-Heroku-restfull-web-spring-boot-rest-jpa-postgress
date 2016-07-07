package com.mycompany.service.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mycompany.application.component.WebApplication;
import com.mycompany.application.component.security.WebSecurityConfigurer;

@SuppressWarnings("deprecation") // Intentional : sample example for spring test 4.* earier releases
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { WebApplication.class, WebSecurityConfigurer .class})
@WebAppConfiguration
public class WebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
