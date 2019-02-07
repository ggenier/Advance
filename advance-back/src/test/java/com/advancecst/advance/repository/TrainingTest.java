package com.advancecst.advance.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

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
public class TrainingTest {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;
    @Inject
    private UserTransaction userTrx;

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void findTrainingInvalid() {
        // Find a Training
        this.em.find(Training.class, null);
    }

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void createInvalidTraining() throws Exception {
        // Creation Identification
        Training training = new Training(null, null, null, null, null, false, null);
        em.persist(training);
    }

    @Test
    @Transactional(TxType.REQUIRES_NEW)
    public void create() throws Exception {
        userTrx.begin();
        TypedQuery<Training> req_findAll = em.createQuery("SELECT f FROM Training f ORDER BY id", Training.class);

        TypedQuery<Long> req_countAll = em.createQuery("SELECT count(f) FROM Training f", Long.class);

        assertEquals(Long.valueOf(0), req_countAll.getSingleResult());
        assertEquals(0, req_findAll.getResultList().size());

        // Creation Adress
        Set<Adress> centerAdress = new HashSet<Adress>();
        centerAdress.add(new Adress(AdressType.PROF, "2", "ICI", "LA", "85000", "TEST", null));

        // Creation Téléphone
        Set<Phone> phonesOraganisme = new HashSet<Phone>();
        phonesOraganisme.add(new Phone(PhoneType.PROF, "0606060606"));

        // Creation Identification
        Identification centerId = new Identification(centerAdress, phonesOraganisme);

        // Creation de l'center
        TrainingCenter center = new TrainingCenter(centerId, "CNAM");

        // Creation trainings
        Training training = new Training(TrainingType.HUIS, "01/01/2019", "04/01/2019", center,
                new Adress(AdressType.PROF, "2", "ICI", "LA", "45000", "TEST", null), false, "bonne formation");

        em.persist(training);

        assertEquals(Long.valueOf(1), req_countAll.getSingleResult());
        assertEquals(1, req_findAll.getResultList().size());

        userTrx.commit();

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(EmployeeRepository.class).addClass(Employee.class)
                .addClass(TransfoDates.class).addClass(Person.class).addClass(EmployeeType.class).addClass(Adress.class)
                .addClass(Phone.class).addClass(Identification.class).addClass(AdressType.class)
                .addClass(PhoneType.class).addClass(Skill.class).addClass(TrainingCenter.class).addClass(Training.class)
                .addClass(TrainingType.class).addClass(SkillType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}