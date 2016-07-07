package com.mycompany.application.component.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mycompany.application.component.WebApplication;
import com.mycompany.application.component.security.WebSecurityConfigurer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { WebApplication.class, WebSecurityConfigurer .class})
@SpringBootTest(classes = { WebApplication.class, WebSecurityConfigurer .class})
@WebAppConfiguration
public class GreetingControllerRestAssuredTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true)
				.apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	public void greeting() throws Exception {

		RestAssuredMockMvc.authentication = RestAssuredMockMvc.with(httpBasic("testuser", "pass"));

		try {
			RestAssuredMockMvc.
					given().
						mockMvc(mockMvc).
					when().
						get("/greeting").
					then().
						statusCode(200).
						body("content", equalTo("Hello, World!"));
		} finally {
			RestAssuredMockMvc.reset();
		}
	}

}
