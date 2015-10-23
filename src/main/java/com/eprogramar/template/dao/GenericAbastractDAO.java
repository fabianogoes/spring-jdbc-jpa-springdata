package com.eprogramar.template.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eprogramar.template.model.GenericAbstractModel;

@Repository
public abstract class GenericAbastractDAO<T> {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public T save(T entity){
		if( ((GenericAbstractModel)entity).getId() != null && ((GenericAbstractModel)entity).getId() > 0 ){
			return em.merge(entity);
		}
		
		em.persist(entity);
		return entity;
	}
	
	public T findById(Long id, Class<T> clazz){
		return em.find(clazz, id);
	}
	
	@Transactional
	public void remove(Long id, Class<T> clazz){
		T entity = this.findById(id, clazz);
		em.remove(entity);
	}
	
	public List<T> findAll(Class<T> clazz){
		return em.createQuery("from "+clazz.getName(), clazz).getResultList();
	}

}
