package br.com.sitedoph.uniph.infraestrutura.persistencia.dao.impl;

import br.com.sitedoph.uniph.dominio.entidades.Usuario;
import br.com.sitedoph.uniph.dominio.repositorios.UsuarioRepositorio;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UsuarioDAO extends GenericDAOHibernate<Usuario> implements UsuarioRepositorio {

    private static final long serialVersionUID = 1L;

    @Override
    public Usuario buscarPorLoginESenha(String login, String senha) {

        Usuario exemplo = new Usuario();
        exemplo.setLogin(login);
        exemplo.setSenha(senha);

        List<Usuario> list = buscarPorExemplo(exemplo);

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
