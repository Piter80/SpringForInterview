package com.petrzimin.springinterview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
//we use sequence from DB.
@SequenceGenerator(name = "users_table_sequence", sequenceName = "users_table_sequence", allocationSize = 1)
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_table_sequence")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Enumerated(value = EnumType.STRING)
	@Column (name = "role")
	private Role role;
	@Enumerated(value = EnumType.STRING)
	@Column (name = "status")
	private Status status;

	public User(String firstName, String lastName, String email, String password,
			Role role, Status status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
	}
}
