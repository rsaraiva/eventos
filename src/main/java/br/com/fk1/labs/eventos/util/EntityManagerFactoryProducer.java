package br.com.fk1.labs.eventos.util;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
@ApplicationScoped
public class EntityManagerFactoryProducer implements ComponentFactory<EntityManagerFactory> {

    private final EntityManagerFactory factory;

    public EntityManagerFactoryProducer() {
        this.factory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public EntityManagerFactory getInstance() {
        return this.factory;
    }
}
