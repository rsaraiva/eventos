package br.com.fk1.labs.eventos.util;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class EntityManagerProducer implements ComponentFactory<EntityManager> {

    private final EntityManager manager;

    public EntityManagerProducer(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    @Override
    public EntityManager getInstance() {
        return this.manager;
    }

    @PreDestroy
    public void fechaManager() {
        this.manager.close();
    }
}
