package com.petrzimin.springinterview.controller;

import com.petrzimin.springinterview.dto.AuthenticationRequestDto;
import com.petrzimin.springinterview.model.User;
import com.petrzimin.springinterview.repository.UserRepository;
import com.petrzimin.springinterview.security.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/v1/auth")
public class AuthenticationRestControllerV1 {

	private final AuthenticationManager authenticationManager;
	private UserRepository repository;
	private JwtTokenProvider provider;

	public AuthenticationRestControllerV1(
			AuthenticationManager authenticationManager,
			UserRepository repository, JwtTokenProvider provider) {
		this.authenticationManager = authenticationManager;
		this.repository = repository;
		this.provider = provider;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getEmail(),
					request.getPassword()
			));
			User user = repository.findByEmail(request.getEmail()).orElseThrow( () -> new UsernameNotFoundException("User doesn't exist"));
			String token = provider.createToken(request.getEmail(), user.getRole().name());
			Map<Object, Object> response = new HashMap<>();
			response.put("email", request.getEmail());
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Invalid username or password", HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, null);
	}

}
