package br.com.sitedoph.uniph.infraestrutura.persistencia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAOHibernate<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "uniph")
    protected EntityManager entityManager;

    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T salvarOuAtualizar(T entidade) {
        return entityManager.merge(entidade);
    }

    public T buscarPorId(Long id) {
        return entityManager.find(getTypeClass(), id);
    }

    public List<T> buscarTodos() {

        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getTypeClass());

        query.select(query.from(getTypeClass()));

        return entityManager.createQuery(query).getResultList();
    }

    public void excluir(T entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);
    }

    public List<T> buscarPorCriteria(Criterion... criteria) {

        Session session = getHibernateSession();

        Criteria crit = session.createCriteria(getTypeClass());

        for (Criterion criterion : criteria) {
            crit.add(criterion);
        }

        return crit.list();
    }

    private Session getHibernateSession() {
        return (Session) entityManager.getDelegate();
    }

    public List<T> buscarPorExemplo(T exemplo, String... propriedadesAExcluir) {

        Example example = Example.create(exemplo);

        example.enableLike(MatchMode.ANYWHERE);
        example.excludeZeroes();
        example.ignoreCase();

        for (String prop : propriedadesAExcluir) {
            example.excludeProperty(prop);
        }

        Session session = getHibernateSession();

        Criteria criteria = session.createCriteria(getTypeClass()).add(example);

        return criteria.list();
    }
}
