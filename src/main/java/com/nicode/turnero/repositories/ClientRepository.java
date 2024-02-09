package com.nicode.turnero.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.nicode.turnero.entities.Client;

public interface ClientRepository extends ListCrudRepository<Client, Long> {

}
