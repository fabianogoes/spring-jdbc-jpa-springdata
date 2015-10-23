package com.eprogramar.template.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eprogramar.template.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
