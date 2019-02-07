package com.advancecst.advance.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class IdentificationTest {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;
    @Inject
    private UserTransaction userTrx;

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void findIdentificationInvalid() {
        // Find a employee
        // Identification identificationTrouvee = identificationRepository.find(null);
        this.em.find(Identification.class, null);
    }

    @Test(expected = Exception.class)
    @Transactional(TxType.REQUIRED)
    public void createInvalidIdentification() throws Exception {
        // Creation Identification
        Identification identification = new Identification(null, null);
        em.persist(identification);
    }

    @Test
    @Transactional(TxType.REQUIRES_NEW)
    public void create() throws Exception {
        userTrx.begin();
        TypedQuery<Identification> req_findAll = em.createQuery("SELECT i FROM Identification i ORDER BY id",
                Identification.class);

        TypedQuery<Long> req_countAll = em.createQuery("SELECT count(i) FROM Identification i", Long.class);

        assertEquals(Long.valueOf(0), req_countAll.getSingleResult());
        assertEquals(0, req_findAll.getResultList().size());

        // Creation Adress
        Adress Adress = new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null);
        Set<Adress> adresses = new HashSet<Adress>();
        adresses.add(Adress);

        // Creation Téléphone
        Phone phone = new Phone(PhoneType.PERSO, "0606060606");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone);

        // Creation Identification
        Identification identification = new Identification(adresses, phones);
        em.persist(identification);

        assertEquals(Long.valueOf(1), req_countAll.getSingleResult());

        // Identification ID
        assertNotNull(identification.getId());

        // Find a identification
        Identification identificationTrouvee = em.find(Identification.class, identification.getId());
        assertNotNull(identificationTrouvee);

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