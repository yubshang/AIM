package com.abet.DAO;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.*;

import java.lang.reflect.ParameterizedType;
//import org.springframework.orm.hibernate4.HibernateCallback;
//import org.springframework.orm.hibernate4.HibernateTemplate;
//import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/** generiac database access object */
public abstract class BaseDAOImpl<T, PK extends java.io.Serializable> implements BaseDAO {
	
	// Use @Resource to inject HibernateTemplate instance
	@Resource
	protected SessionFactory sessionFactory;
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
		if (type instanceof Class) {
			this.entityClass = (Class<T>) type;
		} else if (type instanceof ParameterizedType) {
			this.entityClass = (Class<T>) ((ParameterizedType) type)
					.getRawType();
		}
	}

//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countAll(String clazz) {
//		final String hql = "select count(*) from "+clazz+ " as a";
//		Long count = (Long)super.getHibernateTemplate().execute(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql);
//				query.setMaxResults(1);
//				return query.uniqueResult();
//			}
//		});	
//		return count.intValue();
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countQuery(String hql) {
//		final String counthql = hql;
//		Long count = (Long)super.getHibernateTemplate().execute(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(counthql);
//				query.setMaxResults(1);
//				return query.uniqueResult();
//			}
//		});
//		return count.intValue();
		return 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delById(Class clazz,Serializable id) {
//		super.getHibernateTemplate().delete(super.getHibernateTemplate().load(clazz, id));			
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
//		return super.getHibernateTemplate().find("from "+clazz+" as a order by a.id desc");
		return createCriteria().list();
	}
	
	@SuppressWarnings("rawtypes")
	public List listAll(String clazz, int pageNo, int pageSize) {
//		final int pNo = pageNo;
//		final int pSize = pageSize;
//		final String hql = "from "+clazz+ " as a order by a.id desc";
//		List list = super.getHibernateTemplate().executeFind(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql);
//				query.setMaxResults(pSize);
//				query.setFirstResult((pNo-1)*pSize);
//				List result = query.list();
//				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
//				return result;
//			}
//		});	
//		return list;
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object loadById(Class clazz,Serializable id) {
//		return super.getHibernateTemplate().get(clazz, id);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
//		List list = super.getHibernateTemplate().executeFind(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql1);
//				return query.list();
//			}
//		});			
//		if(list.size()>0)obj=list.get(0);	
		return obj;
	}

	@SuppressWarnings("rawtypes")
	public List query(String hql) {
//		final String hql1 = hql;
//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql1);
//				return query.list();
//			}
//		});	
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List query(String hql, int pageNo, int pageSize) {
//		final int pNo = pageNo;
//		final int pSize = pageSize;
//		final String hql1 = hql;
//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql1);
//				query.setMaxResults(pSize);
//				query.setFirstResult((pNo-1)*pSize);
//				List result = query.list();
//				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
//				return result;
//			}
//		});	
		return null;
	}

	public void saveOrUpdate(Object obj) {
		getCurrentSession().saveOrUpdate(obj);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int update(String hql) {
//		final String hql1 = hql; 
//		return ((Integer)super.getHibernateTemplate().execute(new HibernateCallback(){
//			public Object doInHibernate(Session session) throws HibernateException{
//				Query query = session.createQuery(hql1);
//				return query.executeUpdate();
//			}
//		})).intValue();	
		return 0;
	}
	
//	public Connection getConnection() {
//		return hibernateTemplate.getSessionFactory().getCurrentSession().connection();
//	}
	
	public Criteria createCriteria(){
		return getCurrentSession().createCriteria(entityClass);
	}
}