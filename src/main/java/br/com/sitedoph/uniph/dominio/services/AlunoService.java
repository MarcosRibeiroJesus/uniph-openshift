package br.com.sitedoph.uniph.dominio.services;

import br.com.sitedoph.uniph.dominio.entidades.Aluno;
import br.com.sitedoph.uniph.dominio.repositorios.AlunoRepositorio;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class AlunoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AlunoRepositorio repo;

    public Aluno buscarPorId(Long id) {
        return repo.buscarPorId(id);
    }

    public List<Aluno> buscarTodos() {
        return repo.buscarTodos();
    }

    public void excluir(Aluno aluno) {
        repo.excluir(aluno);
    }

    public Aluno salvarOuAtualizar(Aluno aluno) {
        return repo.salvarOuAtualizar(aluno);
    }

    public List<Aluno> filtrarPorPalavraChave(String filtro) {
        return repo.filtrarPorPalavraChave(filtro);
    }
}
