package com.eprogramar.template.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eprogramar.template.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
