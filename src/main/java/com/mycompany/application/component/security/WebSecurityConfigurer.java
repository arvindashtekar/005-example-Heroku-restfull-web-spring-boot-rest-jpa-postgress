package com.mycompany.application.component.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/static/**").permitAll()
	    	.anyRequest().fullyAuthenticated().
	    	and().httpBasic().
	    	and().csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
  
}

/*@Override
protected void configure(HttpSecurity http) throws Exception {
	 http
        // no session management required
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()        
        .authorizeRequests()
        // the following URLs should be permitted without any authentication
        // this includes our static resources 
        // **Note** : Our landing page ("/") is the login page that 
        //			should not be authenticated, so we add it here  
        .antMatchers("/css/**", "/js/**", "/*.html", "/images/**", "/node_modules/**","/").permitAll()
		//.antMatchers("/resources/**", "/signup", "/about").permitAll()  
        // all other requests must be authenticated
		.anyRequest().fullyAuthenticated().
        // remove basic HTTP authentication - we are writing our own login page 
        and().httpBasic().
        //.and()
        // disable Cross Site Request Forgery token 
        // we do not rely on cookie based auth and are completely stateless so we are safe	    	
    	and().csrf().disable()
        // authentication for token based authentication
    	;
}*/