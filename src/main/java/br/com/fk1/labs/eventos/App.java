package br.com.fk1.labs.eventos;

import br.com.fk1.labs.eventos.model.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    
    static EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("eventos");
    
    public static void main(String[] args) {
        inserir();
        getById(100);
        updateNome();
        getById(100);
        delete();
        listarTodos();
    }
    
    public static void inserir() {
        
        EntityManager em = factory.createEntityManager();
        
        Evento evento = new Evento();
        evento.setId(100);
        evento.setNome("Rock in Rio");
        
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public static void getById(Integer id) {
        
        EntityManager em = factory.createEntityManager();
        
        Evento evento = em.createQuery(
                "select e from Evento e where e.id = :id", Evento.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
        
        System.out.println("********* evento retornado: " + evento.getNome());
        
        em.close();
    }
    
    public static void updateNome() {
        
        EntityManager em = factory.createEntityManager();
        
        Evento evento = em.find(Evento.class, 100);
        evento.setNome("Hue hue BR");
        
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
        
        em.close();
        
    }
    
    public static void listarTodos() {
        
        EntityManager em = factory.createEntityManager();
        
        List<Evento> lista = em.createQuery("select e from Evento e", Evento.class)
                .getResultList();
        
        for (Evento evento : lista) {
            System.out.println(evento.getNome());
        }
        
        em.close();
    }
    
    public static void delete() {
        
        EntityManager em = factory.createEntityManager();
        
        Evento evento = em.find(Evento.class, 100);
        
        em.getTransaction().begin();
        em.remove(evento);
        em.getTransaction().commit();
        
        em.close();
        
    }
}
