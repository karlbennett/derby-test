package org.youthnet.hibernat.embedded.derby.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.youthnet.hibernat.embedded.derby.dao.GenericDao;
import org.youthnet.hibernat.embedded.derby.domain.GenericTable;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 28-Apr-2010
 */
public abstract class GenericDaoDerby<M extends GenericTable, I> implements GenericDao<M, I> {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Class<M> persistentClass;

    protected void setPersistentClass(Class<M> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    @Transactional(readOnly = true)
    public M request(I id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(this.persistentClass);
        criteria.add(Restrictions.eq("id", id));
        criteria.setMaxResults(1);
        return (M) criteria.uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<M> request() {
        return sessionFactory.getCurrentSession().createCriteria(this.persistentClass).list();
    }

    @Override
    @Transactional(readOnly = false)
    public M create(M object) {
        sessionFactory.getCurrentSession().save(object);
        return object;
    }

    @Override
    @Transactional(readOnly = false)
    public M update(M object) {
        sessionFactory.getCurrentSession().update(object);
        return object;
    }

    @Override
    @Transactional(readOnly = false)
    public M delete(I id) {
        M object = this.request(id);
        sessionFactory.getCurrentSession().delete(object);
        return object;
    }

    @Override
    @Transactional(readOnly = false)
    public M delete(M object) {
        sessionFactory.getCurrentSession().delete(object);
        return object;
    }
}
