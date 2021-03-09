package com.petrzimin.springinterview.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {


	USER(Stream.of(Permission.DEVELOPERS_READ).collect(Collectors.toSet())),
	ADMIN(Stream.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE)
			.collect(Collectors.toSet()));

	private final Set<Permission> permissions;

	Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getAuthorities () {
		return  getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
	}
}
