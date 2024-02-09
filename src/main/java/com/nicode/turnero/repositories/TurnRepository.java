package com.nicode.turnero.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.nicode.turnero.entities.Turn;

public interface TurnRepository extends ListCrudRepository<Turn, Long> {

}
