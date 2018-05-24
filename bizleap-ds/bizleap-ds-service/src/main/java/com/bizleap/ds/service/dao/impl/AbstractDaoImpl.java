package com.bizleap.ds.service.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.RootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;

import com.bizleap.commons.domain.enums.EntityStatus;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.ds.service.dao.AbstractDao;

/**
 * @author 
 * @since 1.0.0
 */
@Transactional
public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {

	private Class<E> entityClass;
	private int pageSize = 30;
	
	private static Logger logger = Logger.getLogger(AbstractDaoImpl.class);
	private static Printer printer = new Printer( logger );
 
	protected AbstractDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(E e) throws ServiceUnavailableException {
		try {
			Session session = getCurrentSession();
			session.saveOrUpdate(e);
			session.flush();
			session.evict(e);
		} catch (CannotCreateTransactionException exception) {
			logger.error(exception);
			throw new ServiceUnavailableException(exception);
		} catch (Exception exception) {
			logger.error("\r\nException in save : " + e);			
			throw new ServiceUnavailableException(exception);
		}
	}
	
	@Override
	public List<E> findByString(String queryString, String data) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString).setResultTransformer(RootEntityResultTransformer.INSTANCE).setParameter("dataInput", data);
		entityList = query.list();
		for (E entity : entityList)
			Hibernate.initialize(entity);
		return entityList;
	}

	@Override
	public List<E> findByString(String queryString, String data, String data1) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString).setParameter("dataInput", data).setParameter("dataInput1", data1);
		entityList = query.list();
		for (E entity : entityList)
			Hibernate.initialize(entity);
		return entityList;
	}

	@Override
	public List<E> findByIntegerString(String queryString, Integer data, String data1) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString).setParameter("dataInput", data).setParameter("dataInput1", data1);
		entityList = query.list();
		for (E entity : entityList)
			Hibernate.initialize(entity);
		return entityList;
	}

	@Override
	public List<E> findByString(String queryString) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString);
		entityList = query.list();
		for (E entity : entityList)
			Hibernate.initialize(entity);
		return entityList;
	}	
	
	@Override
	public List<E> findByStringInteger(String queryString, String data, int data1) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString).setParameter("dataInput", data).setParameter("dataInput1", data1);
		entityList = query.list();
		for (E entity : entityList)
			Hibernate.initialize(entity);
		return entityList;
	}
	
	@Override
	public List<E> getAll(String queryString) {	
		
		List<E> entityList;
		
		Query query = getCurrentSession().createQuery(queryString);
		entityList = query.list();
		
		for (E entity : entityList) {
			Hibernate.initialize(entity);
		}		
		return entityList;
	}

	@Override
	public List<E> getAll(String queryString, int pageNumber) {
		List<E> entityList;
		Query query = getCurrentSession().createQuery(queryString);
		query.setFirstResult(pageSize * (pageNumber - 1));
		query.setMaxResults(pageSize);
		entityList = query.list();
		for (E entity : entityList) {
			Hibernate.initialize(entity);
		}
		return entityList;
	}

	@Override
	public long getCount(String queryString) {
		long count;
		Query query = getCurrentSession().createQuery(queryString);
		//query.uniqueResult();
		count = (long) query.list().size();
		return count;
	}
}