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
public class EmployeeRepositoryTest {
    @Inject
    private EmployeeRepository employeeRepository;

    @Test(expected = Exception.class)
    public void findEmployeeInvalid() {
        // Find a employee
        Employee employeeTrouve = employeeRepository.findByHRId(null);
        employeeTrouve.toString();// pour supprimer le warning
    }

    @Test(expected = Exception.class)
    public void createInvalidEmployee() throws Exception {
        Employee employee = new Employee(null, null, null, null, null, null);
        employee = employeeRepository.create(employee);
    }

    @Test
    public void create() throws Exception {
        assertEquals(Long.valueOf(0), employeeRepository.countAll());
        assertEquals(0, employeeRepository.findAll().size());

        // Creation Adress
        Adress adress = new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null);
        Set<Adress> adresses = new HashSet<Adress>();
        adresses.add(adress);

        // Creation Téléphone
        Phone phone = new Phone(PhoneType.PERSO, "0606060606");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone);

        // Creation Identification
        Identification identification = new Identification(adresses, phones);

        Employee employee = new Employee("TEST", "MOI", "MOI", "01/01/2019", EmployeeType.VRP, identification);
        employee = employeeRepository.create(employee);

        assertEquals(Long.valueOf(1), employeeRepository.countAll());

        // Employee ID
        assertNotNull(employee.getId());

        // On controle la construction de l'ID
        assertEquals("TEST", employee.getIdEmployee());

        // Find a employee
        Employee employeeTrouve = employeeRepository.findByTechnicalId(employee.getId());
        assertNotNull(employeeTrouve);

        assertEquals(Long.valueOf(1), employeeRepository.countAll());
        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void createAutoIdValide() throws Exception {
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

        Employee employee = new Employee("", "MOI", "MOI", "01/01/2019", EmployeeType.VRP, identification);
        employee = employeeRepository.create(employee);

        // Employee ID
        assertNotNull(employee.getId());

        // On controle la construction de l'ID
        assertEquals("SAL" + employee.getId().toString(), employee.getIdEmployee());

        // Find a employee
        Employee employeeTrouve = employeeRepository.findByHRId(employee.getIdEmployee());
        assertNotNull(employeeTrouve);
    }

    @Test
    public void createAvecSkill() throws Exception {
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

        // Creation Skill
        Set<Skill> skills = new HashSet<Skill>();
        skills.add(new Skill(SkillType.ENDU, true));
        skills.add(new Skill(SkillType.HUIS, false));

        Employee employee = new Employee("", "MOI", "MOI", "01/01/2019", EmployeeType.VRP, identification, skills,
                null);
        employee = employeeRepository.create(employee);

        // Le employee à deux compétences
        assertEquals(2, employee.getSkills().size());

        // Employee ID
        assertNotNull(employee.getId());

        // On controle la construction de l'ID
        assertEquals("SAL" + employee.getId().toString(), employee.getIdEmployee());

        // Find a employee
        Employee employeeTrouve = employeeRepository.findByTechnicalId(employee.getId());
        assertNotNull(employeeTrouve);
    }

    @Test
    public void createAvecTraining() throws Exception {
        // Creation Adress
        Set<Adress> AdressEmployee = new HashSet<Adress>();
        AdressEmployee.add(new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null));

        // Creation Téléphone
        Set<Phone> phonesEmployee = new HashSet<Phone>();
        phonesEmployee.add(new Phone(PhoneType.PERSO, "0606060606"));

        // Creation Identification
        Identification identificationEmployee = new Identification(AdressEmployee, phonesEmployee);

        // Creation Adress
        Set<Adress> centerAdress = new HashSet<Adress>();
        centerAdress.add(new Adress(AdressType.PROF, "2", "ICI", "LA", "85000", "TEST", null));

        // Creation Téléphone
        Set<Phone> phonesOraganisme = new HashSet<Phone>();
        phonesOraganisme.add(new Phone(PhoneType.PROF, "0606060606"));

        // Creation Identification
        Identification identificationOrganisme = new Identification(centerAdress, phonesOraganisme);

        // Creation de l'center
        TrainingCenter center = new TrainingCenter(identificationOrganisme, "FORMATION TOITURE");

        // Creation trainings
        Set<Training> trainings = new HashSet<Training>();
        trainings.add(new Training(TrainingType.HUIS, "01/01/2019", "04/01/2019", center,
                new Adress(AdressType.PROF, "2", "ICI", "LA", "45000", "TEST", null), false, "une description"));
        trainings.add(new Training(TrainingType.TOIT, "01/01/2019", "04/01/2019", center,
                new Adress(AdressType.PROF, "2", "ICI", "LA", "65000", "TEST", null), false, "une description"));

        Employee employee = new Employee("", "MOI", "MOI", "01/01/2019", EmployeeType.VRP, identificationEmployee, null,
                trainings);
        employee = employeeRepository.create(employee);

        // Le employee à deux trainings
        assertEquals(2, employee.getTrainings().size());

        // Employee ID
        assertNotNull(employee.getId());

        // On controle la construction de l'ID
        assertEquals("SAL" + employee.getId().toString(), employee.getIdEmployee());

        // Find a employee
        Employee employeeTrouve = employeeRepository.findByTechnicalId(employee.getId());
        assertNotNull(employeeTrouve);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(EmployeeRepository.class).addClass(Employee.class)
                .addClass(TransfoDates.class).addClass(Person.class).addClass(EmployeeType.class).addClass(Adress.class)
                .addClass(Phone.class).addClass(Identification.class).addClass(Skill.class).addClass(Training.class)
                .addClass(TrainingCenter.class).addClass(AdressType.class).addClass(PhoneType.class)
                .addClass(SkillType.class).addClass(TrainingType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}