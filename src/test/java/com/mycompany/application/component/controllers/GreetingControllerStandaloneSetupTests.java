package com.mycompany.application.component.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mycompany.application.component.WebApplication;

//MockMvc + Mockito = Epic Tests
@SuppressWarnings("deprecation") // Intentional : sample example for spring test 4.* earier releases
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
public class GreetingControllerStandaloneSetupTests  {

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private FilterChainProxy filterChainProxy;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
   	 mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController())
	    		.dispatchOptions(true)
	            .addFilters(filterChainProxy)
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