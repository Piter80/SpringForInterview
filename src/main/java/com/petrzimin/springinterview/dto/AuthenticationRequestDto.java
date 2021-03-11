package com.petrzimin.springinterview.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
	private String email;
	private String password;

}
