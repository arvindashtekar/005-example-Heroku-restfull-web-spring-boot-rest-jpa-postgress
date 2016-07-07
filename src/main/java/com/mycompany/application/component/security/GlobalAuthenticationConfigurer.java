package com.mycompany.application.component.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mycompany.application.component.security.service.AppUserDetailsService;

@Configuration
class GlobalAuthenticationConfigurer extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  AppUserDetailsService appUserDetailsService;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(appUserDetailsService)
    //.passwordEncoder(passwordencoder())
    ;
	  //auth.inMemoryAuthentication()
	  //.withUser("g").password("g").roles("ADMIN");
  }
  @Bean(name="passwordEncoder")
  public PasswordEncoder passwordencoder(){
	  return new BCryptPasswordEncoder();
  }
 }