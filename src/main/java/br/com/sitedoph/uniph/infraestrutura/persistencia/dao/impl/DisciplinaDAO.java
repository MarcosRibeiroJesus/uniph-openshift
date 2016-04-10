package br.com.sitedoph.uniph.infraestrutura.persistencia.dao.impl;

import br.com.sitedoph.uniph.dominio.entidades.Disciplina;
import br.com.sitedoph.uniph.dominio.repositorios.DisciplinaRepositorio;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DisciplinaDAO extends GenericDAOHibernate<Disciplina> implements DisciplinaRepositorio {

    private static final long serialVersionUID = 1L;

    @Override
    public List<Disciplina> filtrarPorPalavraChave(String filtro) {

        Query query = entityManager.createQuery(
                "SELECT a FROM Disciplina a " +
                        "WHERE " +
                        "lower(a.cargaHoraria) LIKE :filtro OR " +
                        "lower(a.descricao) LIKE :filtro OR " +
                        "lower(a.professor.nomeCompleto) LIKE :filtro");

        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

        List<Disciplina> resultado = query.getResultList();

        return resultado;
    }

//teste

}
