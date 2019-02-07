package com.advancecst.advance.repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;

import com.advancecst.advance.model.Phone;

import java.util.List;

@Transactional(TxType.SUPPORTS)
public class PhoneRepository {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;

    public Phone find(@NotNull Long id) {
        return this.em.find(Phone.class, id);
    }

    @Transactional(TxType.REQUIRED)
    public Phone create(@NotNull Phone phone) {
        em.persist(phone);
        return phone;
    }

    @Transactional(TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Phone.class, id));
    }

    public List<Phone> findAll() {
        TypedQuery<Phone> req = em.createQuery("SELECT t FROM Adress t ORDER BY t.id", Phone.class);
        return req.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> req = em.createQuery("SELECT count(t) FROM Phone t", Long.class);
        return req.getSingleResult();
    }
}