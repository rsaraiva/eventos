package br.com.fk1.labs.eventos.tests;

import br.com.fk1.labs.eventos.model.Endereco;
import br.com.fk1.labs.eventos.model.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CrudPessoaTest {

    static EntityManagerFactory factory;
    
    @BeforeClass
    public static void beforeClass() {
        factory = Persistence.createEntityManagerFactory("eventos_test");
    }
    
    @Test
    public void testaInserePessoa() {
        
        String enderecoString = "Rua Juiz David Barrili, 999 - SJC";
        
        {
            EntityManager em = factory.createEntityManager();

            Pessoa pessoa = new Pessoa();
            pessoa.setId(10);
            pessoa.setNome("Rubens Saraiva");

            Endereco endereco = new Endereco();
            endereco.setId(20);
            endereco.setEndereco(enderecoString);

            pessoa.setEndereco(endereco);

            em.getTransaction().begin();
            em.persist(pessoa);
            em.persist(endereco);
            em.getTransaction().commit();

            em.close();
        }
        
        //assert
        {
            EntityManager em = factory.createEntityManager();
            Pessoa pessoa = em.find(Pessoa.class, 10);
            Assert.assertNotNull(pessoa);
            Assert.assertNotNull(pessoa.getEndereco().getEndereco().equals(enderecoString));
            em.close();
        }
    }
}




