package com.mycompany.application.component.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.mycompany.application.component.WebApplication;
import com.mycompany.application.component.security.WebSecurityConfigurer;

@SuppressWarnings("deprecation") // Intentional : sample example for spring test 4.* earier releases
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { WebApplication.class, WebSecurityConfigurer .class})
//@SpringBootTest(classes = { WebApplication.class, WebSecurityConfigurer .class})
@WebAppConfiguration
//scan ports, set server port to a free one found
@IntegrationTest("server.port=0")
public class GreetingControllerRestTemplateTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    //@Autowired 
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${local.server.port}")
    private int port;
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Test
    public void greeting() throws Exception {
    	String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("testuser:pass").getBytes()));
        
        String uri = "http://localhost:"+port+"/greeting";

        String message;
        try {
        	HttpHeaders headers = new HttpHeaders();
        	headers.add("Authorization", basicDigestHeaderValue);
        	HttpEntity<String> request = new HttpEntity<String>(headers);
        	ResponseEntity<String>  responseEntity =  restTemplate.exchange(uri, HttpMethod.GET, request,String.class);
        	JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        	
//            final ObjectMapper mapper = new ObjectMapper();
//            final JsonNode root = mapper.readTree(response.getBody());
//
//            final JsonNode name = root.path("name");
//            assertNotNull(name);
//
//            final JsonNode owner = root.path("id");
//            assertThat(owner.asText(), is("1"));
//            
        	assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        	assertThat(responseEntity.getHeaders().getContentType(), is(contentType));
        	assertThat(jsonObject.get("content"), is("Hello, World!"));

        } catch (JSONException e) {
            message = "Json Parse Failed";
            Assert.fail(message);
        } catch (HttpMessageNotReadableException e) {
            message = "The GET request FAILED with the message being not readable: " + e.getMessage();
            Assert.fail(message);
        } catch (HttpStatusCodeException e) {
            message = "The GET request FAILED with the HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
            Assert.fail(message);
        } catch (RuntimeException e) {
            //message = "The GET request FAILED " + ExceptionUtils.getFullStackTrace(e);
        	e.printStackTrace();
        	message = "Something wrong";
        	Assert.fail(message);
        }
    }
}
