package com.mycompany.application.component.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
////MockMvc + Mockito = Epic Tests
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mycompany.application.component.WebApplication;
import com.mycompany.application.component.security.WebSecurityConfigurer;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = { WebApplication.class, WebSecurityConfigurer .class})
//@WebAppConfiguration


//Spring test version 4
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { WebApplication.class, WebSecurityConfigurer .class})
@WebAppConfiguration

//testing delete it
//@ContextConfiguration(classes = { WebApplication.class, WebSecurityConfigurer .class})

public class GreetingControllerWebAppContextSetUpTests {

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
//    @Autowired
//    private FilterChainProxy filterChainProxy;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        		.dispatchOptions(true)
        		.apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void greeting() throws Exception {
    	String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("testuser:pass").getBytes()));
        
    	mockMvc.perform(get("/" + "greeting/").header("Authorization",
        		basicDigestHeaderValue).
        		accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.content", is("Hello, World!")));
    }

}