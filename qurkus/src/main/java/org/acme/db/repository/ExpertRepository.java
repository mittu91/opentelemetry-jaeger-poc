package org.acme.db.repository;

import org.acme.db.Expert;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ExpertRepository {

    private final EntityManager entityManager;

    @Inject
    public ExpertRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Expert manageData() {
        return entityManager.find(Expert.class, 1);
    }

    @Transactional
    public Expert persist(Expert expert) {
        entityManager.persist(expert);
        return expert;
    }
}
