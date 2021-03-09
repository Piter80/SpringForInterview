package com.petrzimin.springinterview;

import com.petrzimin.springinterview.model.User;
import com.petrzimin.springinterview.repository.UserRepository;
import com.petrzimin.springinterview.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User doesnt exist"));
		return SecurityUser.fromUser(user);
	}
}
