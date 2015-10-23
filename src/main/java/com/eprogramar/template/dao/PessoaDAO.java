package com.eprogramar.template.dao;

import java.util.List;

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
	
	public Pessoa findById(Long id){
		return em.find(Pessoa.class, id);
	}
	
	@Transactional
	public void remove(Long id){
		Pessoa pessoa = this.findById(id);
		em.remove(pessoa);
	}
	
	public List<Pessoa> findAll(){
		return em.createQuery("from Pessoa", Pessoa.class).getResultList();
	}
}
