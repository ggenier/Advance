package com.advancecst.advance.repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;

import com.advancecst.advance.model.Adress;

import java.util.List;

@Transactional(TxType.SUPPORTS)
public class AdressRepository {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;

    public Adress find(@NotNull Long id) {
        return this.em.find(Adress.class, id);
    }

    @Transactional(TxType.REQUIRED)
    public Adress create(@NotNull Adress Adress) {
        em.persist(Adress);
        return Adress;
    }

    @Transactional(TxType.REQUIRED)
    public Adress update(@NotNull Adress Adress) {
        em.merge(Adress);
        return Adress;
    }

    @Transactional(TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Adress.class, id));
    }

    public List<Adress> findAll() {
        TypedQuery<Adress> req = em.createQuery("SELECT a FROM Adress a ORDER BY a.id", Adress.class);
        return req.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> req = em.createQuery("SELECT count(a) FROM Adress a", Long.class);
        return req.getSingleResult();
    }
}