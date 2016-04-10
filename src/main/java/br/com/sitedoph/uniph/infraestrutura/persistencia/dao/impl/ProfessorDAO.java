package br.com.sitedoph.uniph.infraestrutura.persistencia.dao.impl;

import br.com.sitedoph.uniph.dominio.entidades.Professor;
import br.com.sitedoph.uniph.dominio.repositorios.ProfessorRepositorio;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProfessorDAO extends GenericDAOHibernate<Professor> implements ProfessorRepositorio {

    private static final long serialVersionUID = 1L;

    @Override
    public List<Professor> filtrarPorPalavraChave(String filtro) {

        Query query = entityManager.createQuery(
                "SELECT a FROM Professor a " +
                        "WHERE " +
                        "lower(a.nomeCompleto) LIKE :filtro OR " +
                        "lower(a.cpf) LIKE :filtro OR " +
                        "lower(a.currriculo) LIKE :filtro OR " +
                        "lower(a.email) LIKE :filtro OR " +
                        "lower(a.telefone) LIKE :filtro OR " +
                        "lower(a.dataDeCadastro) LIKE :filtro");

        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

        List<Professor> resultado = query.getResultList();

        return resultado;
    }
}
