package com.petrzimin.springinterview.service;

import com.petrzimin.springinterview.model.Client;
import com.petrzimin.springinterview.repository.ClientsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {


	@Autowired
	private ClientsRepository repository;

	public List<Client> getAll() {
		return repository.findAll();
	}

	public Client findById (Long id) {
		return repository.findById(id).orElse(null);
	}


}
