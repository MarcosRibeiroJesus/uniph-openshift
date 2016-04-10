package br.com.sitedoph.uniph.infraestrutura.persistencia.dao.impl;

import br.com.sitedoph.uniph.dominio.entidades.Turma;
import br.com.sitedoph.uniph.dominio.repositorios.TurmaRepositorio;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TurmaDAO extends GenericDAOHibernate<Turma> implements TurmaRepositorio {

    private static final long serialVersionUID = 1L;

    @Override
    public List<Turma> filtrarPorPalavraChave(String filtro) {

        Query query = entityManager.createQuery(
                "SELECT a FROM Turma a " +
                        "INNER JOIN a.disciplinas d " +
                        "WHERE " +
                        "lower(a.horario) LIKE :filtro OR " +
                        "lower(a.descricao) LIKE :filtro OR " +
                        "lower(d.descricao) LIKE :filtro ");

        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

        List<Turma> resultado = query.getResultList();

        return resultado;
    }
}
