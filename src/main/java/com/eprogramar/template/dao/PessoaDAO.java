package com.eprogramar.template.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eprogramar.template.model.Pessoa;

@Repository
public class PessoaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Pessoa save(Pessoa pessoa){
		if( pessoa.getId() != null && pessoa.getId() > 0 ){
			return em.merge(pessoa);
		}
		
		em.persist(pessoa);
		return pessoa;
	}
	
}
