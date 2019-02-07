package com.advancecst.advance.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.advancecst.advance.model.*;
import com.advancecst.advance.repository.EmployeeRepository;
import com.advancecst.advance.util.*;

@RunWith(Arquillian.class)
@RunAsClient
public class EmployeeEndpointTest {

    @Test
    public void countEmployee(@ArquillianResteasyResource("api/employees") WebTarget webTarget) throws Exception {
        Response response = webTarget.path("countEmployees").request().get();
        // assertEquals(Long.valueOf(0), response.readEntity(Long.class));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void getAllEmployees(@ArquillianResteasyResource("api/employees") WebTarget webTarget) throws Exception {
        Response response = webTarget.path("getAllEmployees").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void createEmployee(@ArquillianResteasyResource("api/employees") WebTarget webTarget) throws Exception {

        // Creation Adress
        Adress Adress = new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null);
        Set<Adress> adresses = new HashSet<Adress>();
        adresses.add(Adress);

        // Creation Téléphone
        Phone phone = new Phone(PhoneType.PERSO, "0606060606");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone);

        // Creation Skill
        Set<Skill> skills = new HashSet<Skill>();
        skills.add(new Skill(SkillType.ENDU, true));
        skills.add(new Skill(SkillType.HUIS, false));

        // Creation Identification
        Identification identification = new Identification(adresses, phones);

        Employee employee = new Employee("", "NOM", "PRENOM", "01/01/2019", EmployeeType.VRP, identification, skills,
                null);

        Response response = webTarget.path("createEmployee").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(employee, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        String location = response.getHeaderString("location");
        assertNotNull(location);
        String employeeId = location.substring(location.lastIndexOf("/") + 1);

        assertEquals("7", employeeId);

    }

    @Test
    public void createEmployeeWithExternalId(@ArquillianResteasyResource("api/employees") WebTarget webTarget)
            throws Exception {

        // Creation Adress
        Adress Adress = new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null);
        Set<Adress> adresses = new HashSet<Adress>();
        adresses.add(Adress);

        // Creation Téléphone
        Phone phone = new Phone(PhoneType.PERSO, "0606060606");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone);

        // Creation Skill
        Set<Skill> skills = new HashSet<Skill>();
        skills.add(new Skill(SkillType.ENDU, true));
        skills.add(new Skill(SkillType.HUIS, false));

        // Creation Identification
        Identification identification = new Identification(adresses, phones);

        Employee employee = new Employee("MO1234", "NOM", "PRENOM", "01/01/2019", EmployeeType.VRP, identification,
                skills, null);

        Response response = webTarget.path("createEmployee").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(employee, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        String location = response.getHeaderString("location");
        assertNotNull(location);
        String employeeId = location.substring(location.lastIndexOf("/") + 1);

        assertEquals("1", employeeId);

    }

    @Deployment
    // @OverProtocol("Servlet 3.0")
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class).addClass(EmployeeRepository.class).addClass(Employee.class)
                .addClass(TransfoDates.class).addClass(LocalDateAdapter.class).addClass(Person.class)
                .addClass(EmployeeType.class).addClass(Adress.class).addClass(Phone.class)
                .addClass(Identification.class).addClass(Skill.class).addClass(Training.class)
                .addClass(TrainingCenter.class).addClass(AdressType.class).addClass(PhoneType.class)
                .addClass(SkillType.class).addClass(TrainingType.class).addClass(EmployeeEndpoint.class)
                .addClass(JAXRSConfiguration.class).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
    }

}
