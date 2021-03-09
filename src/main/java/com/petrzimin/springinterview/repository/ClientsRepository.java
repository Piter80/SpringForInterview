package com.petrzimin.springinterview.repository;

import com.petrzimin.springinterview.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {


}
