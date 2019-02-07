package com.advancecst.advance.repository;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.container.test.api.Deployment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.transaction.Transactional.TxType;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.advancecst.advance.model.*;
import com.advancecst.advance.util.*;

@RunWith(Arquillian.class)
@Transactional(TxType.SUPPORTS)
public class SkillTest {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;
    @Inject
    private UserTransaction userTrx;

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void findSkillInvalid() {
        // Find a Skill
        this.em.find(Skill.class, null);
    }

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void createInvalidSkill() throws Exception {
        // Creation Identification
        Skill skill = new Skill(null, false);
        em.persist(skill);
    }

    @Test
    @Transactional(TxType.REQUIRES_NEW)
    public void create() throws Exception {
        userTrx.begin();
        TypedQuery<Skill> req_findAll = em.createQuery("SELECT c FROM Skill c ORDER BY id", Skill.class);

        TypedQuery<Long> req_countAll = em.createQuery("SELECT count(c) FROM Skill c", Long.class);

        assertEquals(Long.valueOf(0), req_countAll.getSingleResult());
        assertEquals(0, req_findAll.getResultList().size());

        Skill skill = new Skill(SkillType.ENDU, true);

        em.persist(skill);

        assertEquals(Long.valueOf(1), req_countAll.getSingleResult());
        assertEquals(1, req_findAll.getResultList().size());

        userTrx.commit();

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(EmployeeRepository.class).addClass(Employee.class)
                .addClass(TransfoDates.class).addClass(Person.class).addClass(EmployeeType.class).addClass(Adress.class)
                .addClass(Phone.class).addClass(Identification.class).addClass(AdressType.class)
                .addClass(PhoneType.class).addClass(Skill.class).addClass(TrainingCenter.class)
                .addClass(Training.class).addClass(TrainingType.class).addClass(SkillType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}