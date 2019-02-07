package com.advancecst.advance.repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;

import com.advancecst.advance.model.TrainingCenter;

import java.util.List;

@Transactional(TxType.SUPPORTS)
public class TrainingCenterRepository {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;

    public TrainingCenter find(@NotNull Long id) {
        return this.em.find(TrainingCenter.class, id);
    }

    @Transactional(TxType.REQUIRED)
    public TrainingCenter create(@NotNull TrainingCenter trainingCenter) {
        em.persist(trainingCenter);
        return trainingCenter;
    }

    @Transactional(TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(TrainingCenter.class, id));
    }

    public List<TrainingCenter> findAll() {
        TypedQuery<TrainingCenter> req = em.createQuery("SELECT t FROM TrainingCenter t ORDER BY t.id",
                TrainingCenter.class);
        return req.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> req = em.createQuery("SELECT count(t) FROM TrainingCenter t", Long.class);
        return req.getSingleResult();
    }
}