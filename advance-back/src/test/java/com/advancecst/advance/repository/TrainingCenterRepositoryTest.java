package com.advancecst.advance.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.advancecst.advance.model.*;
import com.advancecst.advance.util.*;

@RunWith(Arquillian.class)
public class TrainingCenterRepositoryTest {
    @Inject
    private TrainingCenterRepository trainingCenterRepository;

    @Test(expected = Exception.class)
    public void findTrainingCenterInvalid() {
        // Find a trainingCenter
        TrainingCenter trainingCenterFind = trainingCenterRepository.find(null);
        trainingCenterFind.toString();// pour supprimer le warning
    }

    @Test(expected = Exception.class)
    public void createInvalidTrainingCenter() throws Exception {
        TrainingCenter trainingCenter = new TrainingCenter(null, null);
        trainingCenter = trainingCenterRepository.create(trainingCenter);
    }

    @Test
    public void create() throws Exception {
        assertEquals(Long.valueOf(0), trainingCenterRepository.countAll());
        assertEquals(0, trainingCenterRepository.findAll().size());

        // Creation Adress
        Adress adress = new Adress(AdressType.PROF, "2", "ICI", "LA", "75000", "TEST", null);
        Set<Adress> adresses = new HashSet<Adress>();
        adresses.add(adress);

        // Creation Téléphone
        Phone phone = new Phone(PhoneType.PROF, "0606060606");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone);

        // Creation Identification
        Identification identification = new Identification(adresses, phones);

        TrainingCenter trainingCenter = new TrainingCenter(identification, "CENTRE FORMATION TOURS");
        trainingCenter = trainingCenterRepository.create(trainingCenter);

        assertEquals(Long.valueOf(1), trainingCenterRepository.countAll());

        // TrainingCenter ID
        assertNotNull(trainingCenter.getId());

        // Find a trainingCenter
        TrainingCenter trainingCenterFind = trainingCenterRepository.find(trainingCenter.getId());
        assertNotNull(trainingCenterFind);

        assertEquals(Long.valueOf(1), trainingCenterRepository.countAll());
        assertEquals(1, trainingCenterRepository.findAll().size());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(TrainingCenterRepository.class)
                .addClass(TrainingCenter.class).addClass(TransfoDates.class).addClass(Person.class)
                .addClass(TrainingType.class).addClass(Adress.class).addClass(Phone.class)
                .addClass(Identification.class).addClass(Skill.class).addClass(Training.class)
                .addClass(TrainingCenter.class).addClass(AdressType.class).addClass(PhoneType.class)
                .addClass(SkillType.class).addClass(TrainingType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}