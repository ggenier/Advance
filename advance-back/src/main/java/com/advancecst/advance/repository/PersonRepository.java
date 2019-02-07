package com.advancecst.advance.repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;

import com.advancecst.advance.model.Person;

import java.util.List;

@Transactional(TxType.SUPPORTS)
public class PersonRepository {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;

    public Person find(@NotNull Long id) {
        return this.em.find(Person.class, id);
    }

    @Transactional(TxType.REQUIRED)
    public Person create(@NotNull Person person) {
        em.persist(person);
        return person;
    }

    @Transactional(TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Person.class, id));
    }

    public List<Person> findAll() {
        TypedQuery<Person> req = em.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class);
        return req.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> req = em.createQuery("SELECT count(p) FROM Person p", Long.class);
        return req.getSingleResult();
    }
}