/*
 * (C) Koninklijke Philips Electronics N.V. 2014
 * 
 * All rights are reserved. Reproduction or transmission in whole or in part, in any form or by any
 * means, electronic, mechanical or otherwise, is prohibited without the prior written consent of
 * the copyright owner.
 * 
 * File name: AbstractCRUDRespository.java
 */
package com.philips.refapp.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.philips.refapp.domain.AbstractEntity;
import com.philips.refapp.repository.CRUDRepository;

/**
 * The Class AbstractCRUDRespository.
 *
 * @author Sushanta Dutta
 * @param <T>
 *            the generic type
 * @param <PK>
 *            the generic type
 */
public abstract class AbstractCRUDRespository<T extends AbstractEntity, PK extends Serializable>
		implements CRUDRepository<T, PK> {

	/** The entity manager factory. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The entity class. */
	private Class<T> entityClass;

	/**
	 * Inits the.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.philips.refapp.repository.CRUDRepository#create(java.lang.Object)
	 */
	@Override
	public T create(T t) {
		this.entityManager.persist(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.philips.refapp.repository.CRUDRepository#read(java.io.Serializable)
	 */
	@Override
	public T read(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.philips.refapp.repository.CRUDRepository#update(java.lang.Object)
	 */
	@Override
	public T update(T t) {
		return this.entityManager.merge(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.philips.refapp.repository.CRUDRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.philips.refapp.repository.CRUDRepository#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String query = "select entity from "+this.entityClass.getName()+" entity";
		return this.entityManager.createQuery(query).getResultList();
	}

}
