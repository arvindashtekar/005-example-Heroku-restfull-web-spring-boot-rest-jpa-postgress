package com.mycompany.application.component.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	public AppUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if("testuser".equalsIgnoreCase(username)) {
			return new UserRepositoryUserDetails(username);
		} else{
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
	}
	private final static class UserRepositoryUserDetails extends User implements UserDetails {

		private static final long serialVersionUID = 1L;

		private UserRepositoryUserDetails(String username) {
			super(username, "pass", true, true, true, true,
		                AuthorityUtils.createAuthorityList("ADMIN"));
		}
	}

}
