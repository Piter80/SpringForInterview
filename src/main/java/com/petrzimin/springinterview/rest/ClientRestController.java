package com.petrzimin.springinterview.rest;

import com.petrzimin.springinterview.model.Client;
import com.petrzimin.springinterview.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

	@Autowired
	private ClientService service;

	@GetMapping
	public List<Client> getAll() {
		//while without Service layer
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Client getById(@PathVariable Long id) {
		return service.findById(id);
	}

}
