package com.petrzimin.springinterview.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider provider;

	public JwtTokenFilter(JwtTokenProvider provider) {
		this.provider = provider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		String token = provider.resolveToken((HttpServletRequest) servletRequest);

		try {
			if (token != null && provider.validateToken(token)) {
				Authentication authentication = provider.getAuthentication(token);
				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (JwtAuthenticationException e) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) servletResponse).sendError(e.getStatus().value());
			throw new JwtAuthenticationException("Jwt token is expired or invalid");
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
