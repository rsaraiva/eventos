package br.com.fk1.labs.eventos.tests;

import br.com.fk1.labs.eventos.model.Endereco;
import br.com.fk1.labs.eventos.model.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CrudPessoaTest {
    
    static EntityManagerFactory factory;
    
    @BeforeClass
    public static void init() {
        factory = Persistence.createEntityManagerFactory("eventos_test");
    }
    
    @Test
    public void testaInsercaoPessoaComEndereco() {
        {
            // criar uma pessoa com endereco
            EntityManager em = factory.createEntityManager();

            Pessoa pessoa = new Pessoa();
            pessoa.setId(10);
            pessoa.setNome("Rubens Saraiva");

            Endereco endereco = new Endereco();
            endereco.setId(20);
            endereco.setEndereco("Av Andromeda, 154 - SJC");

            pessoa.setEndereco(endereco);

            // persistir
            em.getTransaction().begin();
            em.persist(pessoa);
            em.persist(endereco);
            em.getTransaction().commit();
            em.close();
        }
        
        {
            // verificar se foi cadastrado ambos
            EntityManager em = factory.createEntityManager();
            Pessoa pessoa = em.find(Pessoa.class, 10);
            Assert.assertNotNull(pessoa);
            Assert.assertNotNull(pessoa.getEndereco().getEndereco().equals("Av Andromeda, 154 - SJC"));
            em.close();
        }
    }
    
    @Test
    public void testaInserePessoaNoMesmoEndereco() {
        // criar uma pessoa com endereco
        EntityManager em = factory.createEntityManager();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(11);
        pessoa.setNome("José da Silva");

        Endereco endereco = new Endereco();
        endereco.setId(20);
        endereco.setEndereco("Av Andromeda, 154 - SJC");

        pessoa.setEndereco(endereco);

        // persistir
        em.getTransaction().begin();
        em.persist(pessoa);
        em.merge(endereco);
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void carregarPessoaComEnderecos() {
        EntityManager em = factory.createEntityManager();
        Endereco endereco = em.find(Endereco.class, 20);
        for (Pessoa p : endereco.getPessoas()) {
            System.out.println("pessoa: " + p.getNome());
        }
        
        em.close();
    }
}
