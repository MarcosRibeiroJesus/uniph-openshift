package br.com.sitedoph.uniph.aplicacao;

import br.com.sitedoph.uniph.dominio.entidades.Usuario;
import br.com.sitedoph.uniph.dominio.repositorios.UsuarioRepositorio;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by ph on 3/25/16.
 */
@Singleton
@Startup
public class StartupBean {

    @Inject
    private UsuarioRepositorio repo;
    private Usuario usuario = new Usuario("user@uniph.com.br", "user", "Usuário Inicial do Sistema", "123456");

    @PostConstruct
    private void startup() {

        System.out.println("**************************************************************************************************");
        System.out.println("**************************************************************************************************");
        System.out.println("INICIALIZANDO O UNIPH                *************************************************************");
        System.out.println("**************************************************************************************************");
        System.out.println("**************************************************************************************************");

        Usuario usuarioNoBanco = repo.buscarPorLoginESenha(usuario.getLogin(), usuario.getSenha());

        if (usuarioNoBanco == null) {
            repo.salvarOuAtualizar(usuario);
        }

    }

    @PreDestroy
    private void shutdown() {
    }
}
