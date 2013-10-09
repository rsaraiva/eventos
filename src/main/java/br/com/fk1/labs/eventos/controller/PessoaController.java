package br.com.fk1.labs.eventos.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fk1.labs.eventos.model.Pessoa;
import javax.persistence.EntityManager;

@Resource
public class PessoaController {
    
    private Result result;
    private EntityManager em;

    public PessoaController(Result result, EntityManager em) {
        this.result = result;
        this.em = em;
    }
    
    public void lista() {
    }
    
    public void adiciona() {
    }
    
    public void salva(Pessoa pessoa) {
        System.out.println("pessoa: " + pessoa.getNome());
        result.redirectTo(PessoaController.class).lista();
    }
}