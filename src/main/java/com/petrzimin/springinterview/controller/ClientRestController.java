package com.petrzimin.springinterview.controller;

import com.petrzimin.springinterview.model.Client;
import com.petrzimin.springinterview.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

	@Autowired
	private ClientService service;

	@GetMapping
	@PreAuthorize("hasAuthority('developers:read')")
	public List<Client> getAll() {
		//while without Service layer
		return service.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public Client getById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('developers:write')")
	public Client create(@RequestBody Client client) {
		service.create(client);
		return client;

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void deleteById(@PathVariable Long id) {
		service.delete(id);
	}
}
