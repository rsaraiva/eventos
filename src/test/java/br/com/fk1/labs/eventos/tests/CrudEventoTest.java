package br.com.fk1.labs.eventos.tests;

import br.com.fk1.labs.eventos.model.Evento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

public class CrudEventoTest {
    
    static EntityManagerFactory factory;
    
    @Test
    public void testaConexao() {
        factory = Persistence.createEntityManagerFactory("eventos_test");
        EntityManager em = factory.createEntityManager();
        assertNotNull(em);
        em.close();
    }
    
    @Test
    public void testaInsercao() {
        
        // insert
        EntityManager em = factory.createEntityManager();
        
        Evento evento = new Evento();
        evento.setId(100);
        evento.setNome("Rock in Rio");
        
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        
        em.close();
        
        // find
        EntityManager emFind = factory.createEntityManager();
        
        assertNotNull(emFind.find(Evento.class, 100));
        
        emFind.close();
    }
    
    @Test
    public void testaAlteracao() {
        String novoEvento = "Monters of Rock!";
        
        EntityManager em = factory.createEntityManager();
        Evento evento = em.find(Evento.class, 100);
        evento.setNome(novoEvento);
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
        em.close();
        
        EntityManager emTest = factory.createEntityManager();
        Evento eventoAlterado = emTest.find(Evento.class, 100);
        assertTrue(eventoAlterado.getNome().equals(novoEvento));
        emTest.close();
    }
}
